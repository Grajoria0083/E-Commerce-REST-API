package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	
	
	
	//									LOGIN EXCEPTIONS
	//								  --------------------
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> loginExceptionHandler(LoginException le, WebRequest wr){
	
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), le.getMessage(),wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	//									CUSTOMER EXCEPTIONS
	//								  -----------------------
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> customerExceptionHandler(CustomerException ce, WebRequest wr){
	
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ce.getMessage(),wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	//									ADMIN EXCEPTIONS
	//								  --------------------
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> adminExceptionHandler(AdminException ae, WebRequest wr){
	
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ae.getMessage(),wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
//    									GLOBAL EXCEPTIONS
//                                 	   -------------------
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> exceptionHandler(Exception le, WebRequest wr){
	
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), le.getMessage(),wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
//			  						PRODUCT EXCEPTIONS
//                                -----------------------
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorDetails> nHFExceptionHandler(ProductException pe, WebRequest wr){
	
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), pe.getMessage(),wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
//									Cart EXCEPTIONS
//  						       -----------------

@ExceptionHandler(CartException.class)
public ResponseEntity<MyErrorDetails> nHFExceptionHandler(CartException ce, WebRequest wr){

MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ce.getMessage(),wr.getDescription(false));

return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
}
	
	
	
	
	
	
//									ORDER EXCEPTIONS
//								   -------------------

@ExceptionHandler(OrderException.class)
public ResponseEntity<MyErrorDetails> nHFExceptionHandler(OrderException oe, WebRequest wr){

MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), oe.getMessage(),wr.getDescription(false));

return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
}
	
	
	
	
	
	
	
//									WRONG URI EXCEPTIONS
//  							   ----------------------

@ExceptionHandler(NoHandlerFoundException.class)
public ResponseEntity<MyErrorDetails> nHFExceptionHandler(NoHandlerFoundException le, WebRequest wr){

MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), le.getMessage(),wr.getDescription(false));

return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
}
	

	
	
	
	
	
//								VALIDATION EXCEPTIONS
//								----------------------

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me) {
		
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),me.getBindingResult().getFieldError().getDefaultMessage(),"validation error");
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<MyErrorDetails> mANVExceptionHandler(MethodArgumentNotValidException le, WebRequest wr){
//	
//		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), le.getMessage(),wr.getDescription(false));
//		
//		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
//	}
}
