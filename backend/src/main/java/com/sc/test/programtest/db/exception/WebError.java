package com.sc.test.programtest.db.exception;

import java.io.Serializable;

import lombok.Data;

/**
 * Simple data struction to carry web error information
 * @author chenhuaquan
 *
 */
@Data
public class WebError implements Serializable {
    private static final long serialVersionUID = 8467553072140041374L;

	private Integer code;
	private String message;
	

    public WebError(Integer c, String m) {
        this.code = c;
        this.message = m;
    }
}
