package com.boot.club.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.club.bean.JoinBean;

public interface JoinMapper extends BaseMapper<JoinBean> {
	// 这个人id和活动id，去查一下这个人有没有报名过该活动
	@Select("select * from tbl_join where myid=#{myid} and aid=#{aid}")
	JoinBean selectJoin(JoinBean bean); // 有点像登录
	
	// @Select @Delete @Update @Insert // 删除这个人myid的报名活动aid
	@Delete("delete from tbl_join where myid=#{myid} and aid=#{aid}")
	void deleteJoin(JoinBean bean);
	
	@Delete("delete from tbl_join where aid=#{aid}")
	void deleteJoinByAid(@Param("aid")int aid);
	
}
