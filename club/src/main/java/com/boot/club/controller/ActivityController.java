package com.boot.club.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.club.bean.ActivityBean;
import com.boot.club.mapper.ActivityMapper;
import com.boot.club.mapper.JoinMapper;
import com.boot.club.mapper.UserMapper;
import com.boot.club.util.NotNullUtil;

@Controller // 千万要记得写
@RequestMapping("/activity")
public class ActivityController extends BaseController {
	@Autowired
	ActivityMapper activityMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	JoinMapper joinMapper;
	// 查询列表
	// http://localhost:8080/activity/list?uid=${user.id}
	@RequestMapping("/list") // 请求对象
	public String list(int uid, HttpServletRequest req) {
		// selectList是BaseMapper中封装好的，当传入null时，就可以得到数据库中所有的数据
		req.setAttribute("uid", uid); // uid直接带入到templates/activity/list.html中
		// 判断这个人的用户名是不是admin，是则为超级管理员
		// 权限管理，超级管理员拥有管理所有数据的权限，其他用户管理数据范围根据实际情况而定
		if ("admin".equals(userMapper.selectById(uid).username)) {
			req.setAttribute("retList", activityMapper.selectAll());
		} else {
			req.setAttribute("retList", activityMapper.selectByUid(uid));
		}
		return "/activity/list"; // 转发，可以打开templates/activity/list.html
	}
	// 请求分2种
	// 1. GET请求
	//	地址栏可见，不太安全
	//  <a href="超链接地址">GET请求/重定向</a>
	//  <form action="地址" method="get"></form>
	
	// 2. POST请求
	//  相对安全，地址栏只能看见地址，看不见地址中的传参。黑客、高手用工具还是可以看到传参
	//  如果参数特别重要，密码传递，get还是post都是不太安全，可以加密解决，这是一个网络安全问题
	//  <form action="地址" method="post"></form>
	
	// @RequestMapping = @GetMapping + @PostMapping
	
	// 按钮点击进来，<a href="">按钮</a>，GET请求
	// http://localhost:8080/activity/add?uid=1
	@GetMapping("/add") // Integer id 1.多带了一个id id不为null 修改   2.id null 添加
	public String add(int uid, Integer id, HttpServletRequest req) {
		req.setAttribute("uid", uid); // 三元表达式 bean null/活动信息
		req.setAttribute("bean", id==null?null:activityMapper.selectById(id));
		return "/activity/add"; // templates/activity/add.html
	}
	// <form action="" method="post"></form>
	@PostMapping("/add")
	public String add(ActivityBean bean, HttpServletResponse resp) {
		if (NotNullUtil.isBlank(bean)) {
			return jsAlert("请完善信息！", 
				"/activity/add?uid="+bean.uid+(bean.id==null?"":"&id="+bean.id), 
					resp);
		}
		if (bean.id == null) { // 添加活动
			bean.ctime = new Date(); // 写入数据时间，就是当前时间，也就是活动的发布时间
			activityMapper.insert(bean);
		} else { // 修改活动
			activityMapper.updateById(bean);
		}
		return "redirect:/activity/list?uid="+bean.uid;
	}
	
	// 删除
	// http://localhost:8080/activity/del
	@RequestMapping("/del")
	public String del(int uid, int id) {
		activityMapper.deleteById(id); // 按照主键id删除这条数据
		joinMapper.deleteJoinByAid(id);
		return "redirect:/activity/list?uid="+uid; // 删除成功，重新刷新列表查询页面
	}
	
}
