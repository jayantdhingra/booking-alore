package com.booking.payloads;



import com.booking.entities.Hotel;
import com.booking.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ReviewsDto {
	
	
	private int reviewId;
	
	private String comments;
	
	private int rating;
	
	
	private User user;
	
	
	private Hotel hotel;

}
