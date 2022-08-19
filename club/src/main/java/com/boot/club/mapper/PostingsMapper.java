package com.boot.club.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.club.bean.ActivityBean;
import com.boot.club.bean.PostingsBean;

public interface PostingsMapper extends BaseMapper<PostingsBean>{


	@Select("select * from v_postings order by id desc")
	List<PostingsBean> selectAll();
	
	@Select("select * from v_postings where uid=#{uid} order by id desc")
	 List<ActivityBean>  selectByuid(@Param("uid")int uid);
	
	@Update("update tbl_postings set clicks=clicks+1 where id=#{id}")
	void updateClicks(int id);
}
