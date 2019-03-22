package com.example.stepan.jpql.user;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.example.stepan.jpql.address.Address;
import com.example.stepan.jpql.wheel.Wheel;
import org.hibernate.LazyInitializationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.stepan.jpql.car.Car;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	UserService s;
	
	
	
	User u;
	User u2;
	Car c;
	Wheel w;
	Wheel w1;
	Address a;

	@Before
	public void init(){
		u = s.createUser();
		u2 = s.createUser();
		c = s.createCar();
		w = s.createWheel();
		w1 = s.createWheel();
		a = s.creatAddress();
	}
	
	@Test
	public void shouldCreateUser(){
		User u = s.createUser();
		assertNotNull(u);
		assertNotNull(u.getId());
	}
	
	@Test
	public void shouldCreateCar(){
		Car u = s.createCar();
		assertNotNull(u);
		assertNotNull(u.getId());
	}
	
	@Test(expected=LazyInitializationException.class)
	public void shouldLoadUserWithoutCars(){
		s.addCarToUser(c, u);
		u = s.findById(u.getId());
		u.getOwnedCars().size();
	}
	
	@Test
	public void shouldLoadUserWithCars(){
		s.addCarToUser(c, u);
		u = s.findUserWithCarsById(u.getId());
		assertEquals(u.getOwnedCars().size(), 1);
	}
	
	
	@Test
	public void shoduldLoadUserWithCarsOnly(){
		s.addCarToUser(c, u);
		List<User> usersThatHaveCar = s.findAllUsersThatHaveCars();
		assertTrue(usersThatHaveCar.size() >= 1);
	}
	
	
	@Test
	public void shouldLoadAllUsersAndTheirCars(){
		s.addCarToUser(c, u);
		List<User> usersThatHaveCar = s.findAllUsersAndTheirCars();
		assertTrue(usersThatHaveCar.size() >= 2);
	}


	@Test(expected=LazyInitializationException.class)
	public void shouldLoadCarWithoutWheels(){
		s.addWheelToCar(w, c);
		c = s.findCarById(c.getId());
		c.getWheels().size();
	}

	@Test(expected=LazyInitializationException.class)
	public void shouldNotLoadWheelsWhenGettingCarFromUser(){

		s.addWheelToCar(w,c);
		s.addCarToUser(c, u);
		User user = s.findUserWithCarsById(u.getId());
		int wheelNum=0;

		for(Car car: user.getOwnedCars()){
			wheelNum = wheelNum+car.getWheels().size();
		}

		assertEquals(wheelNum, 1);
	}


	@Test//(expected=LazyInitializationException.class)
	public void shouldLoadWheelsWhenGettingCarFromUser(){

		s.addWheelToCar(w,c);
		s.addCarToUser(c, u);
		User user = s.findUserWithCarsAndWheelsById(u.getId());
		int wheelNum=0;

		for(Car car: user.getOwnedCars()){
			wheelNum = wheelNum+car.getWheels().size();
		}
		assertEquals(wheelNum, 1);
	}


	@Test
	public void shouldCreateAddress(){
		Address a = s.creatAddress();
		assertNotNull(a);
		assertNotNull(a.getId());
	}


	@Test//(expected=LazyInitializationException.class)
	public void shouldCarAndAddressFromUser(){
		u=s.addCarToUser(c, u);
		s.addAddressToUser(a, u);
		User user = s.findUserWithCarsAndAddressesById(u.getId());
		user.getAddresses();
		user.getOwnedCars();
		assertEquals(user.getAddresses().size(), 1);
	}

	@Test
	public void shouldCarAndAddressAndWheelsFromUser(){
		s.addWheelToCar(w, c);
		u=s.addCarToUser(c, u);
		s.addAddressToUser(a, u);
		User user = s.findUserWithCarsAndWheelsAndAddressesById(u.getId());
		user.getAddresses();
		user.getOwnedCars();
		int wheelNum = 0;
		for(Car car: user.getOwnedCars()){
			wheelNum = wheelNum+car.getWheels().size();
		}
		assertEquals(user.getAddresses().size(), 1);
	}

}
