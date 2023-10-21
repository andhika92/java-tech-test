package com.techtest.consumerbackend.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	
	public static ResponseEntity<Object> generate(HttpStatus httpStatus, String message, Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("data", data);

		return new ResponseEntity<Object>(map, httpStatus);
	}
	
	public static ResponseEntity<Object> generate(HttpStatus httpStatus, String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);

		return new ResponseEntity<Object>(map, httpStatus);
	}
}
