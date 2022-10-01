package com.booking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.entities.Hotel;

public interface HotelRepo extends JpaRepository<Hotel,Integer>{
	
	List<Hotel> findByCityContaining(String city);

}
