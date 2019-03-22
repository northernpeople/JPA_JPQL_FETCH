package com.example.stepan.jpql.user;

import static org.junit.Assert.*;

import java.util.List;

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
	@Before
	public void init(){
		u = s.createUser();
		u2 = s.createUser();
		c = s.createCar();
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

}
