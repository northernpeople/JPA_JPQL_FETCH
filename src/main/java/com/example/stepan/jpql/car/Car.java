package com.example.stepan.jpql.car;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import com.example.stepan.jpql.wheel.Wheel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Setter
@Getter
@ToString(exclude={"name","id"})
@Accessors(chain=true)
public class Car {
	
	@Id
	@Column(columnDefinition="VARCHAR(191)")
	String id;
	
	String name;

		@OneToMany//(fetch = FetchType.EAGER)
	Set<Wheel> wheels = new HashSet<>();
	
	@PrePersist
	public void setId(){
		this.id = UUID.randomUUID().toString();
	}
	
	
	

}
