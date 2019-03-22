package com.example.stepan.jpql.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, String>{

	@Query("select u from User u join fetch u.ownedCars where u.id = ?1")
	User findUserWithCarsById(String userId);
	
	/**
	 * Users without cars not included
	 * @return
	 */
	@Query("select u from User u join fetch u.ownedCars")
	List<User> findUsersWithCars();
	
	
	/**
	 * Users without cars included
	 * @return
	 */
	@Query("select u from User u left join fetch u.ownedCars")
	List<User> findAllUsersAndTheirCars();
}
