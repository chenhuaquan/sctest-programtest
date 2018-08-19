package com.sc.test.programtest.db.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Object for Quiz
 * @author chenhuaquan
 *
 */
@Getter
@Setter
public class QuizDTO extends AbstractDTO {
    
    private String content;
    private String provided;
    private String expected;
    
}
