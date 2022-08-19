package com.boot.club.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.club.bean.ActivityBean;
import com.boot.club.bean.BuyBean;
import com.boot.club.bean.GoodsBean;

public interface GoodsMapper extends BaseMapper<GoodsBean> {
	
	@Select("select * from v_goods where amount>0 order by id desc")
	List<GoodsBean> selectAll(); // 查询全部商品
	@Select("select * from v_goods where uid=#{uid} order by id desc")
	List<GoodsBean> selectByUid(@Param("uid")int uid); // where 按照用户id查，查这个人发布了哪些活动
	@Update("update tbl_goods set amount=amount-#{temp} where id=#{id}")
	void updateAmount(@Param("id")int id,@Param("temp")int temp);
	
	@Update("update tbl_goods set amount=amount+#{temp} where id=#{id}")
	void reUpdateAmount(@Param("id")int id,@Param("temp")int temp);
	
	@Select("select v_goods.* from tbl_buy " +
			"left join v_goods " +
			"on tbl_buy.gid = v_goods.id where cid=#{cid}")
	List<GoodsBean> selectMyBought(@Param("cid")int cid);
}
