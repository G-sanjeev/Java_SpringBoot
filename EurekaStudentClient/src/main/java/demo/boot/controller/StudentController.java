package demo.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class StudentController {
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping
	@CircuitBreaker(name="CourseService" ,fallbackMethod = "defaultMsg")
	public String accessCourse() {
		String msg = restTemplate.getForObject("http://CourseService", String.class);
		return "Successfully accessed course.." +msg;
	}
	public String defaultMsg(Exception e) {
		return "Course not found";
	}
}
