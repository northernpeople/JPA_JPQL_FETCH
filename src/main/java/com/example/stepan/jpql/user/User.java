package com.example.stepan.jpql.user;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.example.stepan.jpql.address.Address;
import com.example.stepan.jpql.car.Car;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Setter
@Getter
@ToString(exclude={"name","id"})
@Accessors(chain=true)
public class User {
	
	@Id
	@Column(columnDefinition="VARCHAR(191)")
	String id;
	
	String name;
	
	// unidirectional one to many
	@OneToMany
	Set<Car> ownedCars = new HashSet<>();

	@OneToMany
	Set<Address> addresses = new HashSet<>();

	
	@PrePersist
	public void setId(){
		this.id = UUID.randomUUID().toString();
	}
	
	
	

}
