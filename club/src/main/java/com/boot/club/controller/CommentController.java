package com.boot.club.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.club.mapper.CommentsMapper;

@Controller
@RequestMapping("/vcomment")
public class CommentController extends BaseController {
	@Autowired
	CommentsMapper commentsMapper;
	
	@RequestMapping("/list")
	public String list(int id, HttpServletRequest req) {
		//得到数据库中所有数据
		req.setAttribute("commentList", commentsMapper.selectCommentsById(id));
		return "/vcomment/list";//转发，打开templates中html
	}
	
	@RequestMapping("/del")
	public String del(int id, int pid) {
		commentsMapper.deleteById(id);
		return "redirect:/vcomment/list?id="+pid;//删除成功，重新刷新
	}

}
