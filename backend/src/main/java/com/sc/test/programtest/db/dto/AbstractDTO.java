package com.sc.test.programtest.db.dto;

import java.util.Date;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sc.test.programtest.db.support.CustomDateDeserializer;
import com.sc.test.programtest.db.support.CustomDateSerializer;

import lombok.Getter;
import lombok.Setter;

/**
 * Base DTO to keep common field definition
 * @author chenhuaquan
 *
 */
@Getter
@Setter
public abstract class AbstractDTO {

	private Long id;
	
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
	private Date lastupdate;
}