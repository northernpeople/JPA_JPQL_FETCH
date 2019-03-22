package com.example.stepan.jpql.wheel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString(exclude={"name","id"})
@Accessors(chain=true)
public class Wheel {
	
	@Id
	@Column(columnDefinition="VARCHAR(191)")
	String id;
	
	String name;
	
	@PrePersist
	public void setId(){
		this.id = UUID.randomUUID().toString();
	}
	
	
	

}
