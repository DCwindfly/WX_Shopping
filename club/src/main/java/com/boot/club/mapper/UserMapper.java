package com.boot.club.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.club.bean.UserBean;

// 1. mapper这个区域和Bean不一样，不是表对应关系，写SQL语句的地方
// 2. 我们也可以不写SQL语句，调用方法实现增删改查，需要继承BaseMapper
//   我在项目中，引入了2个ORM持久层框架，都可以做到调用方法增删改查
// 	 (1)现在市面上流行的MyBatisPlus，基于MyBatis，国内非常认可MyBatis，外国人研发
//			    国外认可Hibernate-->JPA，在国内已经淘汰了
//   (2)中国人研发的BeetlSQL，也挺好用，代码写的比MyBatisPlus还少，在国内没有被认可
// 泛型就是“对应的Bean”。BaseMapper就是MyBatisPlus封装好的东西
public interface UserMapper extends BaseMapper<UserBean> {
	// interface
	//   1. interface是接口，接口里面的方法都是抽象方法，不能写{}
	//   2. 接口interface也可以使用extends继承父接口，直接拥有父类所有函数
	// iBatis(早期) --> MyBatis --> MyBatisPlus
	// 提供的是一套注解 @Select @Delete @Update @Insert
	// 讲JavaWeb时，我们经常会提到?，占位符。?所填充的参数，要通过形参传入进来
	// 这个形参可以是Java类(不用写注解)，也可以是变量@Param("变量名")
	// 基本上一个参数就会有4个一样的单词
	// 这个是MyBatis人家封装好的规则，我们是学习怎么用， 不用深究底层原理
	@Select("select * from tbl_user where username=#{username} and password=#{password}")
	UserBean selectUser(UserBean bean); // 抽象方法，调用该方法就会执行这个方法所对应的SQL语句！
	// 传入用户名和密码，执行select查询语句，就可以查找这个用户名和密码下对应的这个人
	// 查找这个人会得到2种情况：1.找到了，用户名和密码都写对了，登录成功了  2.找不到，用户名或密码错误，再让这个人重新登录

	// @Select("select * from tbl_user where username=#{username}")
	// UserBean selectByUsername(@Param("username")String username); // 带1个参数
	// 有条件参数的SQL还是要自己写一下
	// 要保证Mapper中的方法名不要重复
	
	// 简单的增删改查不用写SQL语句，直接使用BaseMapper封装好的方法就可以了
}
