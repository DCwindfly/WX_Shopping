package com.boot.club;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

@Configuration  // 配置，@Configuration要和@Bean连用
@SpringBootApplication // 把App.java这个类配置成SpringBoot项目的启动类
@MapperScan("com.boot.club.mapper") // 扫描xxxMapper，扫描路径为com.boot.club.mapper
public class App implements WebMvcConfigurer {
	// 1. bean    普通的Java类 --> 对象 = new 类();  可以给这个类添加注解让它和数据表一一对应
	// 2. controller  接收用户请求，表单提交过来的请求，登录表单/注册表单，一个网址对应一个Java方法
	// 3. mapper  SQL语句，增删改查
	// 4. util    工具
	
	// java主函数、主方法，正因为有main函数，所以该项目就可以直接启动起来，可以直接右键，启动该文件
	public static void main(String[] args) {
		SpringApplication.run(App.class, args); // 通过SpringBoot启动该项目
		System.out.println("(♥◠‿◠)ﾉﾞ  后台启动成功   ლ(´ڡ`ლ)ﾞ  \n" ); // 输出笑脸
	}
	

	
//    @Bean
//    public MybatisPlusInterceptor page() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//        return interceptor;
//    }
	
	@Override // 配置上传文件所对应的文件虚拟目录
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/club/**").addResourceLocations("file:D:/create/club/");
	}
	
}
