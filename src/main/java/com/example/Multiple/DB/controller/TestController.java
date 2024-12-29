package com.example.Multiple.DB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Multiple.DB.primary.entity.PrimaryEntity;
import com.example.Multiple.DB.service.LogService;
import com.example.Multiple.DB.service.PrimaryEntityService;

@RestController
@RequestMapping("/api")
public class TestController {
	@Autowired
	private PrimaryEntityService primaryEntityService;

	@Autowired
	private LogService logService;

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		try {
			// Save to primary database
			PrimaryEntity entity = new PrimaryEntity();
			entity.setName("Test Entity");
			primaryEntityService.saveRecord(entity);

			// Log info in secondary database
			logService.logInfo("Successfully saved primary entity");

			return ResponseEntity.ok("Test successful");
		} catch (Exception e) {
			// Log error in secondary database
			logService.logError("Error occurred", e.getStackTrace().toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Test failed");
		}
	}
}