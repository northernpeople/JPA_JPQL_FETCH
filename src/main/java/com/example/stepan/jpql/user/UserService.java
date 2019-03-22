package com.example.stepan.jpql.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stepan.jpql.car.Car;
import com.example.stepan.jpql.car.CarRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo ur;
	
	@Autowired
	CarRepo cr;
	
	public User createUser(){
		return ur.save(new User().setName("JOhn"));
	}
	
	
	public Car createCar(){
		return cr.save(new Car().setName("honda"));
	}
	
	public User addCarToUser(Car c, User u){
		u.getOwnedCars().add(c);
		return ur.save(u);
	}
	 


	public User findById(String id) {
		return ur.findById(id).get();
	}


	public User findUserWithCarsById(String id) {
		return ur.findUserWithCarsById(id);
	}


	public List<User> findAllUsersThatHaveCars() {
		// TODO Auto-generated method stub
		return ur.findUsersWithCars();
	}


	public List<User> findAllUsersAndTheirCars() {
		// TODO Auto-generated method stub
		return ur.findAllUsersAndTheirCars();
	}

}
