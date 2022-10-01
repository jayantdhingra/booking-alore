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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.payloads.ApiResponse;
import com.booking.payloads.AppConstants;
import com.booking.payloads.ReviewResponse;
import com.booking.payloads.ReviewsDto;
import com.booking.services.ReviewsService;

@RestController
@RequestMapping("/api/")
public class ReviewsController {
	
	@Autowired
	private ReviewsService reviewsService;
	
	
	// adding the review
	
	@PostMapping("/user/{userId}/hotel/{hotelId}/reviews")
	public ResponseEntity<ReviewsDto> createReview(@RequestBody ReviewsDto reviewsDto,
			@PathVariable Integer userId,
			@PathVariable Integer hotelId){
		
		ReviewsDto addReview = this.reviewsService.addReview(reviewsDto, userId, hotelId);
		
		return new ResponseEntity<ReviewsDto>(addReview, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/user/{userId}/reviews")
	public ResponseEntity<List<ReviewsDto>> getReviewsByUser(
			@PathVariable Integer userId)
			{
		
		List<ReviewsDto> reviews = this.reviewsService.getReviewsByUser(userId);
		
		return new ResponseEntity<List<ReviewsDto>>(reviews, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/hotel/{hotelId}/reviews")
	public ResponseEntity<List<ReviewsDto>> getReviewsByHotel(
			@PathVariable Integer hotelId)
	{
		
		List<ReviewsDto> reviews = this.reviewsService.getAllReviewsByHotel(hotelId);
		
		return new ResponseEntity<List<ReviewsDto>>(reviews,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/reviews")
	public ResponseEntity<ReviewResponse> getAllReviews(
			@RequestParam(value="pageNumber", defaultValue=AppConstants.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue=AppConstants.SORT_BY,required=false) String sortBy
			){
		
		ReviewResponse reviewResponse = this.reviewsService.getAllReviews(pageNumber, pageSize, sortBy);
		
		return new ResponseEntity<ReviewResponse>(reviewResponse, HttpStatus.OK);
		
	}
	
	// Deleting review
	
	@DeleteMapping("/reviews/{reviewId}")
	public ApiResponse deleteReview(@PathVariable Integer reviewId) {
		
		this.reviewsService.deleteReview(reviewId);
		
		return new ApiResponse("Review has been deleted successfully", true);
		
	}
	
	
	//Updating review
	
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<ReviewsDto> updateReview(@RequestBody ReviewsDto reviewsDto,
			@PathVariable Integer reviewId){
		
		
		ReviewsDto reviewDto = this.reviewsService.updateReview(reviewsDto, reviewId);
		
		return new ResponseEntity<ReviewsDto>(reviewDto, HttpStatus.OK);
		
		
	}
	
	
	

}













































