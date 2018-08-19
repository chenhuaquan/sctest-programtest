package com.sc.test.programtest.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sc.test.programtest.CodeRunner;


@RestController
@RequestMapping(value = "/api/v1/codeexecution", produces = "application/json")
public class CodeExecutionController {
    private static final Logger logger = LoggerFactory.getLogger(CodeExecutionController.class);
	
    @Autowired
    CodeRunner runner;
    
	@PostMapping(value = "/execute")
	public String execute(@RequestBody List<String> codes) {
	    for (String s : codes) {
	        logger.info(s);
	    }
	    String result = null;
	    try {
           result = runner.execute(codes, "55555");
        } catch (IOException e) {
            
            e.printStackTrace();
        }
	    
		return result;
	}
	
}
