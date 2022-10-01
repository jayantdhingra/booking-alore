package com.booking.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.booking.entities.Hotel;
import com.booking.entities.Reviews;
import com.booking.entities.User;
import com.booking.exceptions.ResourseNotFoundException;
import com.booking.payloads.ReviewResponse;
import com.booking.payloads.ReviewsDto;
import com.booking.repo.HotelRepo;
import com.booking.repo.ReviewsRepo;
import com.booking.repo.UserRepo;
import com.booking.services.ReviewsService;

@Service
public class ReviewServiceImpl implements ReviewsService{
	
	@Autowired
	private ReviewsRepo reviewsRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private HotelRepo hotelRepo;
	
	

	@Override
	public ReviewsDto addReview(ReviewsDto reviewsDto, Integer userId, Integer hotelId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("User", "ID", userId));
		
		Hotel hotel = this.hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourseNotFoundException("Hotel", "ID", hotelId));
		
		
		
		Reviews review = this.modelMapper.map(reviewsDto, Reviews.class);
		
		review.setComments(reviewsDto.getComments());
		review.setRating(reviewsDto.getRating());
		review.setUser(user);
		review.setHotel(hotel);
		
		Reviews newReview = this.reviewsRepo.save(review);
		
		
		return this.modelMapper.map(newReview, ReviewsDto.class);
	}
	
	
	

	@Override
	public ReviewsDto updateReview(ReviewsDto reviewsDto, Integer reviewId) {
		
		Reviews review = this.reviewsRepo.findById(reviewId)
				.orElseThrow(() -> new ResourseNotFoundException("Review", "ID", reviewId));
		
		review.setComments(reviewsDto.getComments());
		review.setRating(reviewsDto.getRating());
		
		Reviews updatedReview = this.reviewsRepo.save(review);
		
		
		return this.modelMapper.map(updatedReview, ReviewsDto.class);
	}

	
	
	
	@Override
	public void deleteReview(Integer reviewId) {
		
		Reviews review = this.reviewsRepo.findById(reviewId)
				.orElseThrow(() -> new ResourseNotFoundException("Review", "ID", reviewId));
		
		this.reviewsRepo.delete(review);
		
		
	}

	@Override
	public ReviewResponse getAllReviews(Integer pageNumber, Integer pageSize, String sortBy) {
		
		Sort sort = Sort.by(sortBy);
		
		Pageable p = PageRequest.of(pageNumber,pageSize,sort);
		
		Page<Reviews> pageReview = this.reviewsRepo.findAll(p);
		
		List<Reviews> allReviews = pageReview.getContent();
		
		List<ReviewsDto> reviewDto = allReviews
				.stream()
				.map(reviews -> this.modelMapper.map(reviews, ReviewsDto.class))
				.collect(Collectors.toList());	
		
		ReviewResponse reviewResponse = new ReviewResponse();
		
		
		reviewResponse.setContent(reviewDto);
		reviewResponse.setPageNumber(pageReview.getNumber());
		reviewResponse.setPageSize(pageReview.getSize());
		reviewResponse.setTotalElements(pageReview.getTotalElements());
		reviewResponse.setTotalPages(pageReview.getTotalPages());
		
		reviewResponse.setLastPage(pageReview.isLast());
		
		
		
		return reviewResponse;
	}

	
	
	
	
	
	@Override
	public List<ReviewsDto> getAllReviewsByHotel(Integer hotelId) {
		Hotel hotel = this.hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourseNotFoundException("Hotel", "ID", hotelId));
		
		
		List<Reviews> review = this.reviewsRepo.findByHotel(hotel);
		
		List<ReviewsDto> reviewsDto = review
				.stream()
				.map(reviews -> this.modelMapper.map(reviews, ReviewsDto.class))
				.collect(Collectors.toList());

		
		return reviewsDto;
	}

	@Override
	public List<ReviewsDto> getReviewsByUser(Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("User", "ID", userId));
		
		List<Reviews> review = this.reviewsRepo.findByUser(user);
		
		List<ReviewsDto> reviewsDto = review
				.stream()
				.map(reviews -> this.modelMapper.map(reviews, ReviewsDto.class))
				.collect(Collectors.toList());

		
		return reviewsDto;
	}
	
	

}






























