package com.sc.test.programtest.db.service;

import com.sc.test.programtest.db.dto.QuizDTO;

/**
 * Identify interface to Quiz Service
 * @author chenhuaquan
 *
 */
public interface QuizService {


	QuizDTO findById(Long id);

	
}
