package com.reactiveSpring.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.reactiveSpring.model.Employee;
import com.reactiveSpring.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {

	@Autowired
	public EmployeeRepository employeeRepository;

	@GetMapping(value = "/getAllEmployee", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Employee> getAllEmployees() {

		return employeeRepository.findAll().delayElements(Duration.ofSeconds(1)).log();

	}

	@GetMapping(value = "/getEmployee/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Mono<Employee> getAllEmployees(@PathVariable("id") String id) {

		return employeeRepository.findById(id).log();

	}

}
