package com.booking.payloads;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class HotelDto {
	
	
	private int hotelId;
	
	@NotEmpty
	private String address;
	
	@NotEmpty
	private String country;
	
	@NotEmpty
	private String hotelName;
	
	@NotEmpty
	private boolean availablity;
	
	
	private boolean wifi;
	
	private boolean restaurant;
	
	private boolean acRooms;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private double price;
	
	@NotEmpty
	private float stars;
	
	

}
