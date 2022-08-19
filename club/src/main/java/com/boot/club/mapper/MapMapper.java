package com.boot.club.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.club.bean.GoodsBean;
import com.boot.club.bean.MapBean;

public interface MapMapper extends BaseMapper<MapBean> {

	@Select("select * from tbl_map order by id desc")
	List<MapBean> selectAll(); // 查询全部活动
	@Select("select id,CONCAT_WS(',',title,CONCAT_WS('-',stime,etime)) as title, latitude ,longitude from tbl_map")
	List<MapBean> selectVxAll();
}
