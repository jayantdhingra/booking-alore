package com.booking.services;

import java.util.List;

import com.booking.payloads.ReviewResponse;
import com.booking.payloads.ReviewsDto;

public interface ReviewsService {
	
	// adding a review
	
	ReviewsDto addReview(ReviewsDto reviewsDto, Integer userId, Integer hotelId);
	
	// updating the review 
	
	ReviewsDto updateReview(ReviewsDto reviewsDto, Integer reviewId);
	
	// deleting the review 
	
	void deleteReview(Integer reviewId);
	
	ReviewResponse getAllReviews(Integer pageNumber, Integer pageSize, String sortBy);
	
	// get All reviews by hotel
	
	List<ReviewsDto> getAllReviewsByHotel(Integer hotelId);
	
	// Get all Reviews by User
	
	List<ReviewsDto> getReviewsByUser(Integer userId);
	
	
	
	

}
