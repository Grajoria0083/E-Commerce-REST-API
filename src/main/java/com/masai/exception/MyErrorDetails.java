package com.masai.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyErrorDetails {
	
	private LocalDateTime localDateTime;
	private String details;
	private String msg;
	
}
