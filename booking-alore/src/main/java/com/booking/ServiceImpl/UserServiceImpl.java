package com.booking.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.entities.User;
import com.booking.exceptions.ResourseNotFoundException;
import com.booking.payloads.UserDto;
import com.booking.repo.UserRepo;
import com.booking.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	
	//CREATING THE USER
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
		User savedUser = this.userRepo.save(user);
		
		return this.modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("User", "id", userId));
		
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = this.userRepo.save(user);
		

		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("User", "id", userId));
		
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
				
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> usersDto = users
				.stream()
				.map(user -> this.modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		
		
		return usersDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("User", "id", userId));
		
		this.userRepo.delete(user);
		
		

	}

}
