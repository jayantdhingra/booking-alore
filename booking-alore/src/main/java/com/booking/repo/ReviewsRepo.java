package com.booking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.entities.Hotel;
import com.booking.entities.Reviews;
import com.booking.entities.User;

public interface ReviewsRepo extends JpaRepository<Reviews, Integer>{
	
	List<Reviews> findByUser(User user);
	
	List<Reviews> findByHotel(Hotel hotel);
	
	

}
