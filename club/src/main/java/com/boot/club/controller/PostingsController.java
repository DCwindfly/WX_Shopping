package com.boot.club.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.excel.metadata.Head;
import com.boot.club.bean.ActivityBean;
import com.boot.club.bean.PostingsBean;
import com.boot.club.mapper.CommentsMapper;
import com.boot.club.mapper.PostingsMapper;
import com.boot.club.mapper.UserMapper;
import com.boot.club.util.NotNullUtil;

import ch.qos.logback.classic.pattern.Util;

@Controller
@RequestMapping("/postings")
public class PostingsController extends BaseController {
	@Autowired
	PostingsMapper postingsMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	CommentsMapper commentsMapper;
	
	@RequestMapping("/list")
	public String list(int uid, HttpServletRequest req) {

		req.setAttribute("uid", uid);
		if("admin".equals(userMapper.selectById(uid).username)) {
			req.setAttribute("postingsList", postingsMapper.selectAll());
		}else {
			req.setAttribute("postingsList", postingsMapper.selectByuid(uid));
		}
		return "/postings/list";
	}
	
	
	@GetMapping("/add")
	public String add(int uid, Integer id, HttpServletRequest req) {
		req.setAttribute("uid", uid);
		req.setAttribute("bean", id==null?null:postingsMapper.selectById(id));
		return "/postings/add";
	}
	
	@PostMapping("/add")
	public String add(PostingsBean bean ,HttpServletResponse resp) {
		if(NotNullUtil.isBlank(bean)) {
         return jsAlert("璇峰畬鍠勪俊鎭�", "/postings/add?uid="+bean.uid+(bean.id==null?"":"&id="+bean.id)
        		 , resp);
		}
		postingsMapper.updateById(bean);
		
		return "redirect:/postings/list?uid="+bean.uid;
		
	}
	
	@RequestMapping("/del")
	public String del(int uid, int id) {
		postingsMapper.deleteById(id);
		//缂哄皯涓�涓猚ommerMapper 鍚庢湡鍔� 鍒繕浜�
		return "redirect:/postings/list?uid="+uid;//鍒犻櫎鎴愬姛锛岄噸鏂板埛鏂�
	}
		
}
