package com.sc.test.programtest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/app", produces = "application/json")
public class AppController {

	@Value("${app.version}")
	private String version;
	
	@GetMapping(value = "/version", produces = "text/plain")
	public String version() {
		return "version " + version;
	}

}
