package com.boot.club.bean;

import java.util.List;

// 跟数据表没什么关系，跟小程序有关，不需要写set get方法
public class VxResp { // Java给小程序响应类，包含小程序需要的所有数据
	public int code = 1; // 响应码  1.成功  0.失败，默认code 为 1 成功
	public String msg = ""; // 响应提示语，失败，就要给出一个友好的错误提示，发给小程序
	// 失败函数
	public void fail(String msg) {
		this.code = 0; // 让它失败 0
		this.msg = msg;
	}
	
	public String openid; // 有可能会用到openid
	// myid有可能是null
	public Integer myid; // 当前登录用户的id，tbl_user id
	public List<ActivityBean> activity; // 活动数组/活动列表
	
	public List<ActivityBean> hot; // 热门活动列表，由活动组成
	
	
	//跳蚤市场（姚步辉）
	
	public List<GoodsBean> goods;
	
	//跳蚤市场（姚步辉）
	
	//地图（姚步辉）
	
	public List<MapBean> map;
	
	//地图（姚步辉）
	
	/*---------------------------------------------------------------------------*/
	// 新的内容
	public List<PostingsBean> postings;//帖子列表
	public List<CommentsBean> comments;//帖子列表
	public String username;//用户姓名
	/*---------------------------------------------------------------------------*/
}
