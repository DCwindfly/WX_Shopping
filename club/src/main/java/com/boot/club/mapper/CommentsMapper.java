package com.boot.club.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.club.bean.CommentsBean;
import com.boot.club.bean.PostingsBean;

public interface CommentsMapper extends BaseMapper<CommentsBean>{
	@Select("select * from v_comment  where pid=#{pid} order by id desc")
	List<CommentsBean> selectCommentsById(@Param("pid")int pid);
}
