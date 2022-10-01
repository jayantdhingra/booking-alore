package com.booking.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.payloads.ApiResponse;
import com.booking.payloads.UserDto;
import com.booking.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	//Creating User
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		
		UserDto createdUserDto = this.userService.createUser(userDto);
		
		return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
		
	}
	
	// Updating User
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Integer userId){
		
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
		
	}
	
	// Getting user By ID
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
		
		UserDto userDto = this.userService.getUserById(userId);
		
		return new ResponseEntity<UserDto>(userDto , HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUSers(){
		
		List<UserDto> users  = this.userService.getAllUsers();
		
		return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		
		this.userService.deleteUser(userId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User has been deleted", true), HttpStatus.OK);
		
	}
	
	

}
