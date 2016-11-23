package com.example.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	
	public SqlSessionFactoryUtil(){
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}
	
	public SqlSessionFactory getSqlSessionFactory(){
		return sqlSessionFactory;
	}
}
