package com.example.stepan.jpql.car;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

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
	
	@PrePersist
	public void setId(){
		this.id = UUID.randomUUID().toString();
	}
	
	
	

}
