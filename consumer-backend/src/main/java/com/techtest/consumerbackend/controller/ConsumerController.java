package com.techtest.consumerbackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techtest.consumerbackend.model.Consumer;
import com.techtest.consumerbackend.repository.ConsumerRepository;
import com.techtest.consumerbackend.response.ResponseHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@CrossOrigin
@RestController
@RequestMapping("/consumers")
public class ConsumerController {

	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Autowired
	private Validator validator;
	
	@GetMapping
	public ResponseEntity<Object> searchConsumer() {
		try {
			List<Consumer> ListConsumer = consumerRepository.findAll();

			return ResponseHandler.generate(HttpStatus.OK, "Data retrieved successfully", ListConsumer);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generate(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findConsumer(@PathVariable int id) {
		try {
			// check existing consumer
			Consumer consumer = consumerRepository.findById(id).orElse(null);
			if (consumer == null) {
				return ResponseHandler.generate(HttpStatus.NOT_FOUND, String.format("Data with ID %d not found", id));
			}

			return ResponseHandler.generate(HttpStatus.OK, "Data retrieved successfully", consumer);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generate(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> addConsumer(@RequestBody Consumer consumer) {
		try {
			// basic validation
			Set<ConstraintViolation<Consumer>> violations = validator.validate(consumer);
			if (!violations.isEmpty()) {
				List<String> errors = new ArrayList<String>();
				for (ConstraintViolation<Consumer> violation : violations) {
					errors.add(violation.getMessage());
				}
				return ResponseHandler.generate(HttpStatus.BAD_REQUEST, "Data create failed", errors);
		    }

			// add consumer
			Consumer newConsumer = consumerRepository.save(consumer);
			
			return ResponseHandler.generate(HttpStatus.OK, "Data created successfully", newConsumer);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generate(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> editConsumer(@RequestBody Consumer consumer) {
		try {
			// basic validation
			Set<ConstraintViolation<Consumer>> violations = validator.validate(consumer);
			if (!violations.isEmpty()) {
				List<String> errors = new ArrayList<String>();
				for (ConstraintViolation<Consumer> violation : violations) {
					errors.add(violation.getMessage());
				}
				return ResponseHandler.generate(HttpStatus.BAD_REQUEST, "Data create failed", errors);
		    }
			
			// check existing consumer
			Consumer editedConsumer = consumerRepository.findById(consumer.getId()).orElse(null);
			if (editedConsumer == null) {
				return ResponseHandler.generate(HttpStatus.NOT_FOUND, String.format("Data with ID %d not found", consumer.getId()));
			}
			
			// update consumer
			editedConsumer.setNama(consumer.getNama());
			editedConsumer.setAlamat(consumer.getAlamat());
			editedConsumer.setKota(consumer.getKota());
			editedConsumer.setProvinsi(consumer.getProvinsi());
			editedConsumer.setTglRegistrasi(consumer.getTglRegistrasi());
			editedConsumer.setStatus(consumer.getStatus());
			consumerRepository.save(editedConsumer);

			return ResponseHandler.generate(HttpStatus.OK, "Data updated successfully", editedConsumer);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generate(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteConsumer(@PathVariable int id) {
		try {
			consumerRepository.deleteById(id);
			
			return ResponseHandler.generate(HttpStatus.OK, String.format("Data with ID %d deleted successfully", id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generate(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
		}
	}
}
