package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.EmployeeEntity;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.service.EmployeeService;


import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "user")// Đánh dấu nơi chứa API

public class EmployeeController {
	@Autowired
	EmployeeService service;
	

	@GetMapping("/list")
	public ResponseEntity<List<EmployeeEntity>> findAll() {
		return ResponseEntity.ok(service.getAllEmployees());
	}

	@PutMapping(path = "/edit/{id}")
	public EmployeeEntity update(@RequestParam("id") Long id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email ) throws RecordNotFoundException {

		if (service.getEmployeeById(id) == null) {
			ResponseEntity.badRequest().build();
			
		}
		service.createOrUpdateEmployee(new EmployeeEntity(id, firstName, lastName, email));
		return service.getEmployeeById(id);
	
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws RecordNotFoundException {
		if (service.getEmployeeById(id) == null) {
			ResponseEntity.badRequest().build();
		}
		service.deleteEmployeeById(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping(path = "/create")
	public EmployeeEntity create(@RequestParam("id") Long id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email ) throws RecordNotFoundException {
		service.createOrUpdateEmployee(new EmployeeEntity(id, firstName, lastName, email));
		return service.getEmployeeById(id);
	
	}

}
