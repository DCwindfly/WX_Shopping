package com.boot.club.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.club.bean.MapBean;
import com.boot.club.mapper.MapMapper;
import com.boot.club.mapper.UserMapper;
import com.boot.club.util.NotNullUtil;

@Controller // 千万要记得写
@RequestMapping("/map")
public class MapController extends BaseController {
	@Autowired
	MapMapper mapMapper;
	@Autowired
	UserMapper userMapper;
	@RequestMapping("/list")
	public String list(int uid, HttpServletRequest req) {
		req.setAttribute("uid", uid);
		if ("admin".equals(userMapper.selectById(uid).username)) {
			req.setAttribute("retList", mapMapper.selectAll());
		}
		return "/map/list";
	}
	@GetMapping("/add")
	public String add(int uid, Integer id, HttpServletRequest req) {
		req.setAttribute("uid", uid);
		req.setAttribute("bean", id==null?null:mapMapper.selectById(id));
		return "/map/add";
	}

	@PostMapping("/add")
	public String add(MapBean bean, HttpServletResponse resp) {
		if (NotNullUtil.isBlank(bean)) {
			return jsAlert("请完善信息！", 
				"/map/add?uid="+bean.id+(bean.id==null?"":"&id="+bean.id), 
					resp);
		}
		if (bean.id == null) { // 添加活动
			mapMapper.insert(bean);
		} else { // 修改活动
			mapMapper.updateById(bean);
		}
		return "redirect:/map/list?uid="+'1';
	}
	
	// 删除
	// http://localhost:8080/activity/del
	@RequestMapping("/del")
	public String del(int uid, int id) {
		mapMapper.deleteById(id); // 按照主键id删除这条数据
		return "redirect:/map/list?uid="+uid; // 删除成功，重新刷新列表查询页面
	}
	
}
