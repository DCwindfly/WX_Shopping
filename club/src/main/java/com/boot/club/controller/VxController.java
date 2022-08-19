package com.boot.club.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.club.bean.ActivityBean;
import com.boot.club.bean.BuyBean;
import com.boot.club.bean.CommentsBean;
import com.boot.club.bean.GoodsBean;
import com.boot.club.bean.JoinBean;
import com.boot.club.bean.MapBean;
import com.boot.club.bean.PostingsBean;
import com.boot.club.bean.UserBean;
import com.boot.club.bean.VxResp;
import com.boot.club.mapper.ActivityMapper;
import com.boot.club.mapper.BuyMapper;
import com.boot.club.mapper.CommentsMapper;
import com.boot.club.mapper.GoodsMapper;
import com.boot.club.mapper.JoinMapper;
import com.boot.club.mapper.MapMapper;
import com.boot.club.mapper.PostingsMapper;
import com.boot.club.mapper.UserMapper;

// 微信小程序专用Controller
@Controller
@RequestMapping("/vx")
public class VxController extends BaseController {
	
	//地图（姚步辉）
	
	@Autowired
	MapMapper mapMapper;
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	BuyMapper buyMapper;
	//跳蚤市场（姚步辉）
	
	/*--------------------------------------------------------------------------*/
	// 新注入到spring中的mapper
	@Autowired
	PostingsMapper postingsMapper;
	@Autowired
	CommentsMapper commentsMapper;
	/*--------------------------------------------------------------------------*/
	
	@Autowired
	UserMapper userMapper;
	@Autowired
	ActivityMapper activityMapper;
	@Autowired
	JoinMapper joinMapper; // mapper先注入到Spring中
	//    	api=.../vx/login
	// @RequestMapping = @GetMapping + @PostMapping
	@RequestMapping("/login")
	public void login(UserBean bean, HttpServletResponse resp) {
//		System_out_println(bean);
		VxResp vx = new VxResp(); // 默认成功
		UserBean user = userMapper.selectUser(bean);
		if (user == null) {
			vx.fail("用户名或密码错误");
		} else {
			vx.myid = user.id; // 把登录用户的用户id返给小程序
		}
		outRespJson(vx, resp); // 把vx对象输出给小程序/网页
	}
	
	//   /vx/activity
	@RequestMapping("/activity") // myid由小程序传入
	public void activity(int myid, HttpServletResponse resp) {
		VxResp vx = new VxResp();
		vx.activity = activityMapper.selectAllByMyid(myid);
		vx.hot = activityMapper.selectHot();
		for (ActivityBean bean : vx.activity) {
			bean.vxtime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(bean.ctime);
		}
		outRespJson(vx, resp);
	}
	// @RequestMapping = @GetMapping + @PostMapping
	@PostMapping("/join") //  util.http('地址', this.data, resp=>{  })
	public void join(JoinBean bean, HttpServletResponse resp) {
		VxResp vx = new VxResp();
		if (joinMapper.selectJoin(bean) != null) {
			vx.fail("您已经报名过该活动了~"); // 报名失败
		} else {
			joinMapper.insert(bean); // 直接把 aid myid 写入到 tbl_join 表中
		}
		outRespJson(vx, resp);
	}
	@GetMapping("/join")  //  util.http('/vx/join?myid='+this.data.myid, resp=>{  })
	public void join(int myid, HttpServletResponse resp) {
		VxResp vx = new VxResp(); // 第一句话都是vx = new VxResp()给小程序的数据
		vx.activity = activityMapper.selectMyActivity(myid);
		outRespJson(vx, resp); // 最后一句话都是outRespJson(vx, resp); // 固定写法
	}
	@RequestMapping("/unjoin") // id  myid  aid
	public void unjoin(JoinBean bean, HttpServletResponse resp) {
		VxResp vx = new VxResp();
		joinMapper.deleteJoin(bean); // 删除报名
		outRespJson(vx, resp);
	}
	//跳蚤市场（姚步辉）
	
	@RequestMapping("/goods")
	public void goods(int myid, HttpServletResponse resp) {
		VxResp vx = new VxResp();
		vx.goods = goodsMapper.selectAll();
		outRespJson(vx, resp);
	}
	
	@RequestMapping("/buy")
	public void buy(BuyBean bean, HttpServletResponse resp){
		VxResp vx = new VxResp();
		buyMapper.insert(bean);
		goodsMapper.updateAmount(bean.gid, bean.amount);
		outRespJson(vx, resp);
	}
	
	@RequestMapping("/bought")
	public void bought(int myid,HttpServletResponse resp){
		VxResp vx = new VxResp();
		vx.goods = goodsMapper.selectMyBought(myid);
		outRespJson(vx, resp);
	}
	
	@RequestMapping("/cancel")
	public void cancel(BuyBean bean,HttpServletResponse resp){
		VxResp vx = new VxResp();
		goodsMapper.reUpdateAmount(bean.gid,buyMapper.amount(bean.id));
		outRespJson(vx, resp);
	}
	
	//跳蚤市场（姚步辉）
	//地图（姚步辉）
	@RequestMapping("/map")
	public void mark(MapBean bean, HttpServletResponse resp){
		VxResp vx = new VxResp();
		vx.map = mapMapper.selectVxAll();
		outRespJson(vx, resp);
	}
	//地图（姚步辉）
	/*-----------------------------------------------------------------------------*/
	//以下为新加内容
	@RequestMapping("/postings")
	public void postings(HttpServletResponse resp) {
		VxResp vx = new VxResp();
		vx.postings = postingsMapper.selectAll();
		for(PostingsBean bean : vx.postings) {
			bean.vxtime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(bean.ptime);
		}
		outRespJson(vx, resp);
	}
	
	@RequestMapping("/addpostings")//发布帖子
	public void addpostings(PostingsBean bean, HttpServletResponse resp) {
		VxResp vx = new VxResp();
		bean.ptime = new Date();
		bean.clicks = 0;
		postingsMapper.insert(bean);
		outRespJson(vx, resp);
	}
	
	@RequestMapping("/comments")//展示所有评论,传入pid
	public void comments(int pid , HttpServletResponse resp) {
		VxResp vx = new VxResp();
		//System_out_println("kgpdfgofdmhbfg");
		vx.comments = commentsMapper.selectCommentsById(pid);
		for(CommentsBean bean : vx.comments) {
			bean.vxtime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(bean.ptime);
		}
		outRespJson(vx, resp);
	}
	
	@RequestMapping("/addcomments")//发布评论
	public void addcomments(CommentsBean bean, HttpServletResponse resp) {
		VxResp vx = new VxResp();
		bean.ptime = new Date();
		commentsMapper.insert(bean);
		outRespJson(vx, resp);
	}
	
	@RequestMapping("/clicks")
	public void clicks(int id, HttpServletResponse resp) {
		VxResp vx = new VxResp();
		postingsMapper.updateClicks(id);
		outRespJson(vx, resp);
	}
	/*--------------------------------------------------------------------------*/
}
