package com.example.Sehedule.Exceptiohandler;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.Sehedule.CustomException.StatusTpyeNotNullException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException e)
	{
		Map<String, String> errorsMap = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(error->{
			errorsMap.put(error.getField(),error.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errorsMap);
	}
	
	@ExceptionHandler(StatusTpyeNotNullException.class)
	public ResponseEntity<Map<String, String>> handlecustomexception(StatusTpyeNotNullException ex)
	{
		Map<String, String> erMap = new HashMap<>();
		erMap.put("error Message", ex.getMessage());
	
	return ResponseEntity.badRequest().body(erMap);
}
}
