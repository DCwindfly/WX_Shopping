package com.boot.club.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tbl_join")
public class JoinBean { // 用户表和活动的关系表，可以表示 多对多关系
	@TableId(type=IdType.AUTO) // 1活动-->多个人
	public Integer id;		//    1 人 --> 多个活动
	public Integer myid;// 用户id --> 用户表
	public Integer aid; // 活动id --> 活动表
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMyid() {
		return myid;
	}
	public void setMyid(Integer myid) {
		this.myid = myid;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
	
	
}
