package com.sc.test.programtest.db.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for Quiz
 * @author chenhuaquan
 *
 */
@Getter
@Setter
//@EqualsAndHashCode(callSuper=false)
public class Quiz extends AbstractEntity{

    private String content;
    private String provided;
    private String expected;
}