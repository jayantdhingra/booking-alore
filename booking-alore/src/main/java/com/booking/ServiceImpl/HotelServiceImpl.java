package com.booking.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.entities.Hotel;
import com.booking.exceptions.ResourseNotFoundException;
import com.booking.payloads.HotelDto;
import com.booking.repo.HotelRepo;
import com.booking.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HotelRepo hotelRepo;
	
	

	@Override
	public HotelDto addHotel(HotelDto hotelDto) {
		
		Hotel hotel = this.modelMapper.map(hotelDto, Hotel.class);
		
		Hotel updatedHotel = this.hotelRepo.save(hotel);
		
		
		return this.modelMapper.map(updatedHotel, HotelDto.class);
	}

	
	
	@Override
	public HotelDto updateHotel(HotelDto hotelDto, Integer hotelId) {
		
		Hotel hotel = this.hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourseNotFoundException("Hotel","ID",hotelId));
		
		hotel.setAddress(hotelDto.getAddress());
		hotel.setAvailablity(hotelDto.isAvailablity());
		hotel.setAcRooms(hotelDto.isAcRooms());
		hotel.setCity(hotelDto.getCity());
		hotel.setCountry(hotelDto.getCountry());
		hotel.setHotelName(hotelDto.getHotelName());
		hotel.setWifi(hotelDto.isWifi());
		hotel.setStars(hotelDto.getStars());
		hotel.setPrice(hotelDto.getPrice());
		hotel.setRestaurant(hotelDto.isRestaurant());
		
		Hotel updatedHotel = this.hotelRepo.save(hotel);
		
		return this.modelMapper.map(updatedHotel, HotelDto.class);
		
	}

	@Override
	public HotelDto getHotelById(Integer hotelId) {
		Hotel hotel = this.hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourseNotFoundException("Hotel","ID",hotelId));
		
		
		return this.modelMapper.map(hotel, HotelDto.class);
	}

	
	
	@Override
	public List<HotelDto> getAllHotels() {
		List<Hotel> hotels = this.hotelRepo.findAll();
		
		
		List<HotelDto> hotelsDto = hotels
				.stream()
				.map(hotel -> this.modelMapper.map(hotel, HotelDto.class))
				.collect(Collectors.toList());
				
		
		
		return hotelsDto;
	}

	@Override
	public void deleteHotel(Integer hotelId) {
		
		Hotel hotel = this.hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourseNotFoundException("Hotel","ID",hotelId));
		
		this.hotelRepo.delete(hotel);

	}



	@Override
	public List<HotelDto> searchHotel(String keyword) {
		
		List<Hotel> hotels = this.hotelRepo.findByCityContaining(keyword);
		
		List<HotelDto> hotelDtos = 
				hotels
				.stream()
				.map(hotel -> this.modelMapper.map(hotel, HotelDto.class))
				.collect(Collectors.toList());
		
		
		return hotelDtos;
	}

}




































































