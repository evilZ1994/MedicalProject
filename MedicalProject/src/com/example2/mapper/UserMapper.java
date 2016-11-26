package com.example2.mapper;

import com.example2.bean.User;

public interface UserMapper {
	
	public void addUser(User user);
	
	public User getUserById(int id);
}
