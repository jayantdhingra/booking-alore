package com.booking.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="hotel")
@Getter
@Setter
@NoArgsConstructor
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int hotelId;
	
	private String address;
	
	private String country;
	
	private String hotelName;
	
	private boolean availablity;
	
	private boolean wifi;
	
	private boolean restaurant;
	
	private boolean acRooms;
	
	private String city;
	
	private double price;
	
	private float stars;
	
	@OneToMany(mappedBy="hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Reviews> review = new ArrayList<>();
	
	
}
