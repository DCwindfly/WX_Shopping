package com.boot.club.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.club.bean.ActivityBean;
import com.boot.club.bean.BuyBean;

public interface BuyMapper extends BaseMapper<BuyBean> {
	// 简单的SQL语句不需要写
	@Select("select * from v_goods order by id desc")
	List<BuyBean> selectAll(); // 查询全部活动
	
	
	// SQL语句，已经是最大难度了
	@Select("select v_goods.*, tbl.cid from v_goods " +
			"left join " +
			"(select * from tbl_buy where cid=#{cid}) tbl " +
			"on v_goods.id = tbl.gid order by id desc")
	List<BuyBean> selectAllByMyid(@Param("cid")int cid);
	// 查询未售空商品
	@Select("select * from v_goods where amount > 0")
	List<BuyBean> selectOnSale();
	@Select("select amount from tbl_buy where id=#{id} tbl")
	int amount(@Param("id")int id);
	
}
