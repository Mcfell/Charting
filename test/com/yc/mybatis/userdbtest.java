package com.yc.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.chart.bean.User;

import junit.framework.TestCase;

public class userdbtest extends TestCase{
		private Reader reader;  
	    private SqlSessionFactory sqlSessionFactory;  
	  
	    @Before  
	    public void setUp() throws Exception {  
	        try {  
	            reader = Resources.getResourceAsReader("mybatis.xml");  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);  
	    }  
  
		@Test  
		public void testUpdate(){
			String resource = "mybatis.xml";
	        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
	        InputStream is = userdbtest.class.getClassLoader().getResourceAsStream(resource);
	        //构建sqlSession的工厂
	        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
	        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
	        //Reader reader = Resources.getResourceAsReader(resource); 
	        //构建sqlSession的工厂
	        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
	        //创建能执行映射文件中sql的sqlSession
	        SqlSession session = sessionFactory.openSession(); 
			User u = new User();
			u.setNickname("test");
			u.setPassword("1234"); 
		        try {  
		           String value =	session.selectOne("com.chart.Mapper.UserMapper.selectUser");
		           // int d = session.insert("com.chart.Mapper.UserMapper.insertUser", u);  
		            System.out.println("success"+value);
		        } finally {  
		            session.close();  
		        }  
			
		}
}
