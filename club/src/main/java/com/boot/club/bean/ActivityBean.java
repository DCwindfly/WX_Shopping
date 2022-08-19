package com.boot.club.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.club.util.NotNull;

@TableName("tbl_activity")
public class ActivityBean {
	
	@TableField(exist=false) // 一定要写
	public Integer myid;
	public Integer getMyid() {
		return myid;
	}
	public void setMyid(Integer myid) {
		this.myid = myid;
	}


	@TableField(exist=false) // tbl_activity表中没有username
	public String username; // 关联查询，大表中有username
	@NotNull
	public String logo; // 数据表多写了一个logo
	@TableId(type=IdType.AUTO) // 自动增长
	public Integer id;
	@NotNull
	public String activity; // 活动名称
	@NotNull
	public Integer price; // 报名费
	@NotNull
	public String detail; // 活动介绍
	@NotNull
	public String start; // 字符串时间
	public Date ctime; // java.util.Date 固定格式时间
	
	// 它不是数据表中的字段，是我们为了让小程序能够多显示一个合理的时间
	@TableField(exist=false) // 必须要加该注解
	public String vxtime; // 多写一个vxtime，专门给小程序用
	// 对 vxtime 生成set get
	public String getVxtime() {
		return vxtime;
	}
	public void setVxtime(String vxtime) {
		this.vxtime = vxtime;
	}
	
	
	public Integer uid;
	public Integer hot;
	// 重新生成一遍 set get 方法  @Data，在eclipse不太好配置，各种报错，idea中用，没有压力
	// 1.受累，自己生成一遍set get，2.idea写代码
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	
	
}
