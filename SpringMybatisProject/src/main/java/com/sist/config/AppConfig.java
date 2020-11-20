package com.sist.config;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;

import javax.activation.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages={"com.sist.*"})
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
	
	
	
	// HandlerMapping, HandlerAdapter, HttpRequestHandler 셋팅
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
	}


	// BasicDataSource
	@Bean
	public BasicDataSource datasource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@211.238.142.195:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		
		return ds;
	}
	
	
	// SqlSessionFactory 
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
		ssf.setDataSource(datasource());
		return ssf.getObject();
	}
	
	
	
	// MapperFactoryBean
	@Bean
	public MapperFactoryBean mapperFactoryBean() throws Exception{
		MapperFactoryBean m = new MapperFactoryBean();
		m.setSqlSessionFactory(sqlSessionFactory());
		m.setMapperInterface(com.sist.dao.MusicMapper.class);
		return m;
	}
	
	
	
	// viewResolver
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver r = new InternalResourceViewResolver();
		r.setPrefix("/");
		r.setSuffix(".jsp");
		return r;
	}
	
}



