package com.demo.aws.controllers;

import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.aws.service.FileService;

@RestController
public class FileController {

	private static final String MESSAGE_1 = "Uploaded the file successfully";
	private static final String FILE_NAME = "fileName";

	@Autowired
	private FileService fileService;

	@GetMapping(value = "/file")
	public ResponseEntity<Object> findByName(@RequestParam("fileName") String fileName) {
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(Duration.ofSeconds(30)))
				.header("Content-type", "application/octet-stream")
				.header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
				.body(new InputStreamResource(fileService.findByName(fileName)));

	}
	

	@GetMapping(value = "/fileCount")
	public ResponseEntity<Object> findKeyCount() {
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(Duration.ofSeconds(30)))
				.header("Content-type", "application/json")
				.body(fileService.findKeyCount());

	}
	
	

}
