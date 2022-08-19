package com.boot.club.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.club.util.NotNull;

// 帖子
@TableName("tbl_postings")
public class PostingsBean {
	@TableId(type = IdType.AUTO)
	public Integer id;
	public Integer uid;//发帖的用户id
	public Date ptime;//发帖时间
	@NotNull
	public String detail;
	@NotNull
	public Integer clicks;
	
	/*---------------------------------------------------------------------------*/
	// 以下为新修改的内容
	@TableField(exist = false)
	public String username; // 评论人用户名
	@TableField(exist = false) 
	public String photo; // 头像
	@TableField(exist = false)
	public String vxtime; // 转换后的微信显示时间	
	/*----------------------------------------------------------------------------*/
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Date getPtime() {
		return ptime;
	}
	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Integer getClicks() {
		return clicks;
	}
	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getVxtime() {
		return vxtime;
	}
	public void setVxtime(String vxtime) {
		this.vxtime = vxtime;
	}
}
