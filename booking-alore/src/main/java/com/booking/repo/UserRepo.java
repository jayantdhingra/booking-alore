package com.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
