package com.boot.club.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.club.util.NotNull;

// 评论
@TableName("tbl_comment")
public class CommentsBean {

	@TableId(type = IdType.AUTO)
	public Integer id;
	public Integer uid;//评论用户id
	public Date ptime;//发表时间
	@NotNull
	public String detail;//内容
	public Integer pid;//帖子id
	@TableField(exist = false)
	public String username;//用户名
	@TableField(exist = false)
	public String photo;//头像
	@TableField(exist = false)
	public String vxtime; // 转换后的微信显示时间
	
	
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
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getVxtime() {
		return vxtime;
	}
	public void setVxtime(String vxtime) {
		this.vxtime = vxtime;
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

}
