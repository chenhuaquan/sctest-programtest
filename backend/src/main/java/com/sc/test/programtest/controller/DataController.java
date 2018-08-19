package com.sc.test.programtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sc.test.programtest.db.dto.QuizDTO;
import com.sc.test.programtest.db.exception.WebError;
import com.sc.test.programtest.db.service.QuizService;


@RestController
@RequestMapping(value = "/api/v1/data", produces = "application/json")
public class DataController {
    
    @Autowired
    private QuizService quizService;


    @GetMapping("/quiz/{id}")
    public ResponseEntity<?> get(@PathVariable final Long id){
        try {
            final QuizDTO dto = quizService.findById(id);
            return new ResponseEntity<QuizDTO>(dto, HttpStatus.OK);
        } catch (final RuntimeException e) {
            return new ResponseEntity<WebError>(new WebError(1, e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
    }
    
}
