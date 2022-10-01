package com.booking.services;

import java.util.List;

import com.booking.payloads.HotelDto;

public interface HotelService {
	
	HotelDto addHotel(HotelDto hotelDto);
	
	HotelDto updateHotel(HotelDto hotelDto, Integer hotelId);
	
	HotelDto getHotelById(Integer hotelId);
	
	List<HotelDto> getAllHotels();
	
	void deleteHotel(Integer hotelId);
	
	List<HotelDto> searchHotel(String keyword);

}
