package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class CurrentUserSession {

	@Id
	@Column(unique = true)
	private Integer cCustomerId;
	
	private String uuid;
	
	private LocalDateTime localDateTime;
	
}
