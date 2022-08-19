package com.boot.club.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.boot.club.bean.UserBean;
import com.boot.club.mapper.UserMapper;
import com.boot.club.util.FileUtil;
import com.boot.club.util.NotNullUtil;

// SpringBoot + MyBatisPlus + MySQL
// SpringBoot = SpringMvc + Spring + Tomcat + 边角料
// SpringMvc部分替代Servlet，成为了JavaWeb中最核心的Mvc组件
// Spring：它是一个容器，管理各个组件，还提供了面向切面编程这种方式
// MyBatis：增删改查框架
// 我们至此还没有提及Tomcat，要知道Tomcat不是消失了，也不是没用
// 我们用了Tomcat了，Tomcat在SpringBoot框架中，SpringBoot内置了一个Tomcat服务器中间件
// 我们在启动App.java主函数时，其实就是在启动SpringBoot中的Tomcat
// Tomcat默认端口号是8080，http://localhost:8080

// Mapper跟数据库传递数据
// Controller跟网页传递数据
// 数据库  <--> Java Mapper <--> Java Controller <--> 网页/小程序
@Controller // 将UserController变成一个控制器，可以和网页/小程序进行交互
public class UserController extends BaseController {
	@Autowired // 作用：跟new差不多，注入到Spring容器中
	UserMapper userMapper; // UserController成员变量这个地方，创建出了一个UserMapper
	
	// http://localhost:8080/logo
	@RequestMapping("/logo")
	public void logo(MultipartFile file, HttpServletResponse resp) { // 上传图片、视频、word、ppt 这些都是文件
		String filePath = "D:/create/club/logo"; // 如果你电脑没有D盘
		//			create是根文件夹       项目名   logo图片
		FileUtil.createFile(filePath); // 创建文件夹结构
		String fileName = file.getOriginalFilename();//得到文件的原名
		// transferTo 转存
		try {
			file.transferTo(new File(filePath + "/" + fileName));
		} catch (Exception e) {
			System_out_println("有可能是文件夹路径写错了，检查一下文件夹路径下有没有转存好这张图");
		} // D:/create/club/logo/图片名.jpg
		outRespJson("/club/logo/"+fileName, resp); // response将数据输出给网页中
	}
	// 总结：数据输出给网页有2种方式
	// 1.转发。通过request将数据带入到网页中
	//	 req.setAttribute("retList", 数据);
	//   return "/activity/list"; FreeMarker模版引擎，能将 数据 和 html结构 一起输出到网页中
	// 2.outRespJson() 封装方法，在BaseController父类中
	//	 response将数据输出给网页
	//	 outRespJson(数据, resp); 只能将纯数据输出给网页，不带样式、不带标签
	
	@RequestMapping("/register")
	public String register(UserBean bean) {
		// 解决方案 1.加判断，判断一下这个人存不存在  2.try catch有的时候还挺好用，但不能多用
		if (NotNullUtil.isBlank(bean)) { // 判空校验 1.后端(java)判空校验 2.前端(js)判空校验(不讲)
			// 后端校验和前端校验区别？
			// 后端校验 1.数据可以保证是安全可靠的
			//		 2.数据要通过请求提交给java，java的压力就会比较大，
			//		 java是运行在Tomcat中，对电脑内存的消耗就会比较大
			// 前端校验 1.浏览器压力大，Tomcat没有压力，因为没有将数据提交给java
			//		 2.不太安全
			// 大项目、大公司中采用的策略是：先有前端校验，可以减轻Tomcat压力，后有后端校验，还可以保证数据安全
			return "redirect:/index.html?msg="+getUTF8Param("用户名或密码不能为空");
		}
		try {
			userMapper.insert(bean); // 钱七硬写到表中，受到表唯一键约束，巧妙使用报错
		} catch (Exception e) {
			// e.printStackTrace();
			return "redirect:/index.html?msg="+getUTF8Param(bean.username+"已经注册了");
		}
		return "redirect:/index.html?msg="+getUTF8Param(bean.username+"注册成功！"); // 用户提示语
	}
	
	// http://localhost:8080/login
	// 此时，就给login方法配置了一个可以访问到的网址
	@RequestMapping("/login")
	public String login(UserBean bean) {
		UserBean user = userMapper.selectUser(bean);
		if (user == null) { // 这个人没找到，登录失败，让它重新登录
			return "redirect:/index.html?msg="+getUTF8Param("用户名或密码错误"); // 支持中文
		} else {
			return "redirect:/main?uid="+user.id; // 重定向 redirect:
		}
	}
	
	// 什么时候需要重启，记忆一下：
	// eclipse：
	//	改Java代码，要“关闭”，然后重启
	//  改网页代码，不需要重启，只需要刷新网页即可
	// idea：
	//	改Java代码，要重启，改网页代码也需要重启
	
	// 总结：
	//  转发：return "/main";  末尾给地址拼接上.html后缀，/main.html
	//		1. 去templates文件夹下找到对应的网页打开   templates/main.html
	//		2. 可以使用req.setAttribute("变量名", 值); 带到网页中，网页里就可以使用 ${ 取值 }
	//		3. 不可以在地址末尾拼接参数，拼接上了也无法带入到网页中
	//		4. 不可以打开Java配置的Mapping网址
	
	//  重定向：return "redirect:/index.html";
	//		1. 去static文件夹下找到对应的网页打开    static/index.html
	//		2. 不可以使用req.setAttribute( , );
	//		3. 重定向可以在地址栏末尾拼接上要带入到网页中的参数
	//		  return "redirect:/index.html?msg=用户名或密码错误"
	//		4. 重定向可以打开Java Controller里面的@xxxMapping("/网址")
	//		5. <a href="超链接地址">按钮</a> 它是重定向，不是转发！
	
	// http://localhost:8080/main
	@RequestMapping("/main") // 这是Servlet，可以带参数到网页中
	public String main(int uid, HttpServletRequest req) {
		// user = 把这个id对应的这个人全部信息查取出来(id,username,password)   带到网页中
		req.setAttribute("user", userMapper.selectById(uid)); // req.setAttribute("变量名", 变量值); 带到网页中
		return "/main"; // 转发：templates/main.html
	}
	
	// main.html在templates文件夹下
	// index.html在static文件夹下
	
	// templates
	//	里面的网页，只能通过java间接打开
	// static
	//	里面的网页，可以浏览器输入地址直接打开
	
}
