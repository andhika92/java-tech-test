package com.techtest.consumerbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techtest.consumerbackend.model.Wilayah;
import com.techtest.consumerbackend.repository.WilayahRepository;
import com.techtest.consumerbackend.response.ResponseHandler;

@CrossOrigin
@RestController
@RequestMapping("/provinces")
public class WilayahController {
	
	@Autowired
	private WilayahRepository wilayahRepository;
	
	@GetMapping
	public ResponseEntity<Object> searchConsumer() {
		try {
			List<Wilayah> ListWilayah = wilayahRepository.findProvinces();

			return ResponseHandler.generate(HttpStatus.OK, "Data retrieved successfully", ListWilayah);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generate(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
		}
	}
	
	@GetMapping("/{provinceCode}")
	public ResponseEntity<Object> searchConsumer(@PathVariable String provinceCode) {
		try {
			List<Wilayah> ListWilayah = wilayahRepository.findRegenciesByProvince(provinceCode);

			return ResponseHandler.generate(HttpStatus.OK, "Data retrieved successfully", ListWilayah);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generate(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
		}
	}
}
