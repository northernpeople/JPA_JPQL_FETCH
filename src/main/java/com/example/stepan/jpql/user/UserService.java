package com.example.stepan.jpql.user;

import java.util.List;

import com.example.stepan.jpql.address.Address;
import com.example.stepan.jpql.address.AddressRepo;
import com.example.stepan.jpql.wheel.Wheel;
import com.example.stepan.jpql.wheel.WheelRepo;
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

	@Autowired
	WheelRepo wr;

	@Autowired
	AddressRepo ar;
	
	public User createUser(){
		return ur.save(new User().setName("JOhn"));
	}
	
	
	public Car createCar(){
		return cr.save(new Car().setName("honda"));
	}

	public Wheel createWheel(){
		return wr.save(new Wheel().setName("ww"));
	}

	public User addCarToUser(Car c, User u){
		u.getOwnedCars().add(c);
		return ur.save(u);
	}

	public Car addWheelToCar(Wheel w, Car c){
		c.getWheels().add(w);
		return cr.save(c);
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

	public Car findCarById(String id) {
		return cr.findById(id).get();
	}

	public List<User> findAllUsersAndTheirCars() {
		// TODO Auto-generated method stub
		return ur.findAllUsersAndTheirCars();
	}


	public User findUserWithCarsAndWheelsById(String id) {
		return ur.findUserWithCarsAndWheelsById(id);
	}

	public Address creatAddress(){
		return ar.save(new Address().setName("Hamillton Street"));
	}

	public User addAddressToUser(Address a, User u){
		System.out.println("a.getId() = " + a.getId());
		u.getAddresses().add(a);
		return ur.save(u);
	}


	public User findUserWithCarsAndAddressesById(String id) {
		return ur.findUserWithCarsAndAddressById(id);
	}

	public User findUserWithCarsAndWheelsAndAddressesById(String id) {
		return ur.findUserWithCarsAndAddressAndWheelsById(id);
	}
}
