package com.sc.test.programtest.db.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Base Entity to keep common field definition
 * @author chenhuaquan
 *
 */
@Getter
@Setter
public class AbstractEntity {

    private Long id;
    
    private Date lastupdate;
	
}
