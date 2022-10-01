package com.booking.Controller;

import java.util.List;

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
import com.booking.payloads.HotelDto;
import com.booking.services.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	
	@PostMapping("/")
	public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto){
		
		HotelDto hotelDtos = this.hotelService.addHotel(hotelDto);
		
		return new ResponseEntity<HotelDto>(hotelDtos, HttpStatus.CREATED);

	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<HotelDto> getById(@PathVariable Integer hotelId){
		
		HotelDto hotel = this.hotelService.getHotelById(hotelId);
		
		return new ResponseEntity<HotelDto>(hotel,HttpStatus.OK);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<HotelDto>> getAllHotels(){
		
		List<HotelDto> hotels = this.hotelService.getAllHotels();
		
		return new ResponseEntity<List<HotelDto>>(hotels,HttpStatus.OK);
	}
	
	
	@PutMapping("/{hotelId}")
	public ResponseEntity<HotelDto> updateHotel(@RequestBody HotelDto hotelDto,@PathVariable Integer hotelId){
		
		
		HotelDto hoteldto = this.hotelService.updateHotel(hotelDto, hotelId);
		
		return new ResponseEntity<HotelDto>(hoteldto,HttpStatus.OK);

	}
	
	
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<ApiResponse> deleteHotel(@PathVariable Integer hotelId){
		
		this.hotelService.deleteHotel(hotelId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Hotel has been Deleted", true), HttpStatus.OK);
		
		
	}
	
	// Search Hotel By City
	
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<HotelDto>> searchHotel(
			@PathVariable("keywords") String keywords){
		
		
		
		List<HotelDto> hotels = this.hotelService.searchHotel(keywords);
		
		return new ResponseEntity<List<HotelDto>>(hotels, HttpStatus.OK);
		
		
		
	}
	
	
	
	

}
