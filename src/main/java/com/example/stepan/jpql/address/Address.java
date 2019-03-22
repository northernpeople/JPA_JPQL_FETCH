package com.example.stepan.jpql.address;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString(exclude={"name","id"})
@Accessors(chain=true)
public class Address {
	
	@Id
	@Column(columnDefinition="VARCHAR(191)")
	String id;
	
	String name;
	
	@PrePersist
	public void setId(){
		this.id = UUID.randomUUID().toString();
	}
	
	
	

}
