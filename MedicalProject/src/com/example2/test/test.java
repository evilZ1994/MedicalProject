package com.example2.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example2.bean.User;
import com.example2.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration("/Config/beans.xml")
public class test {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void config2Test(){
		
		User user = new User();
		user.setName("Albert");
		user.setAge(25);
		
		userMapper.addUser(user);
		
	}
}
