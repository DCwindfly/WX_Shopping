package com.boot.club.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tbl_buy")
public class BuyBean { // 用户表和活动的关系表，可以表示 多对多关系
	@TableId(type=IdType.AUTO) // 1活动-->多个人
	public Integer id;		//    1 人 --> 多个活动
	public Integer cid;// 用户id --> 用户表
	public Integer gid; // 活动id --> 活动表
	public Integer amount;
	
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	
}
