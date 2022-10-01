package com.booking.services;

import java.util.List;

import com.booking.payloads.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto , Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);

}
