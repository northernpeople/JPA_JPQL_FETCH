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


	@Query("select u from User u join fetch u.ownedCars c join fetch c.wheels where u.id = ?1")
	User findUserWithCarsAndWheelsById(String userId);


	@Query("select u from User u left join fetch u.ownedCars  left join fetch u.addresses where u.id = ?1")
	User findUserWithCarsAndAddressById(String userId);


	@Query("select u from User u left join fetch u.ownedCars c  left join fetch u.addresses left join fetch c.wheels where u.id = ?1")
	User findUserWithCarsAndAddressAndWheelsById(String userId);
}
