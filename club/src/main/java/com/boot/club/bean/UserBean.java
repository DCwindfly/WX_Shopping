package com.boot.club.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.club.util.NotNull;
// 第一遍听懂，第二遍熟练，第三遍是搬砖
// 代码规范：1.类名 大驼峰命名 UserBean
//		2.变量名 方法名/函数名 小驼峰命名 userBean
// 如果代码不规范：调错难度会变得非常大
// 你会发现eclipse没有提示，想要有提示 1. Alt + / (不推荐) 2. 设置提示，让eclipse从此和idea一样智能提示
// 				.abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
// 使用提示，如果这个类，这个注解不是我们写的，或者不在同一个包下，
// 		此时用这个类/注解就需要导包，使用提示就可以帮助我们导包
@TableName("tbl_user") // 此时通过@TableName(表名)注解就可以将数据表和JavaBean对应起来，并且一一对应
public class UserBean { // 表中有哪些字段，Bean就会有哪些成员变量
	@TableId(type=IdType.AUTO) // AUTO_INCREMENT 自动增长
	public Integer id; // 尽量整型使用Integer类型，Integer是int的包装类，Integer比int更加强大
	// 唯一键不考虑 // Integer是对象，int是基本类型，int和Integer都可以接收纯数字，Integer还可以接收null
	@NotNull
	public String username; // 讲Java基础知识，我会推荐咱们直接用private，我就比较推荐使用public
	@NotNull					// public特性，直接调用成员变量，方便，代码少，我们都是简单起见
	public String password; // 虽然这些成员变量是public类型，但我们也要麻烦一点，还是把set get生成出来
	
	
	
	
	
	
	
	// 每添加一个成员变量，set get都要随手跟一下
	// 这些set get方法，我们也不用，谁用？框架用，没有set get，框架在某些代码下就失灵了
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
