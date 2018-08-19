/**
 * Copyright Yotta Ltd 2017-2018
 */
package com.sc.test.programtest.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.test.programtest.db.converter.QuizConverter;
import com.sc.test.programtest.db.dto.QuizDTO;
import com.sc.test.programtest.db.mapper.QuizMapper;

/**
 * Service implementation for QuizService
 * @author chenhuaquan
 *
 */
@Transactional(readOnly = true)
@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizMapper quizMapper;
    
    /* (non-Javadoc)
     * @see com.sc.test.programtest.db.service.QuizService#findById(java.lang.Long)
     */
    @Override
    public QuizDTO findById(Long id) {
        return QuizConverter.toDTO(quizMapper.findById(id));
    }

}
