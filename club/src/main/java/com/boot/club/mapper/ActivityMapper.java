package com.boot.club.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.club.bean.ActivityBean;

public interface ActivityMapper extends BaseMapper<ActivityBean> {
	// 简单的SQL语句不需要写
	@Select("select * from v_activity where uid=#{uid} order by id desc")
	List<ActivityBean> selectByUid(@Param("uid")int uid); // where 按照用户id查，查这个人发布了哪些活动
	@Select("select * from v_activity order by id desc")
	List<ActivityBean> selectAll(); // 查询全部活动
	
	// SQL语句，已经是最大难度了
	@Select("select v_activity.*, tbl.myid from v_activity " +
			"left join " +
			"(select * from tbl_join where myid=#{myid}) tbl " +
			"on v_activity.id = tbl.aid order by id desc")
	List<ActivityBean> selectAllByMyid(@Param("myid")int myid);
	// 查询热门活动
	@Select("select * from v_activity where hot = 1")
	List<ActivityBean> selectHot();
	// 查询我的报名
	@Select("select v_activity.* from tbl_join " +
			"left join v_activity " +
			"on tbl_join.aid = v_activity.id where myid=#{myid}")
	List<ActivityBean> selectMyActivity(@Param("myid")int myid);
	
}
