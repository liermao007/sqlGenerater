package com.xian.generator.exception;

/**
 * 查询异常
 * @author lgx
 */
public class QueryException extends RuntimeException {

    public QueryException(String message, Throwable cause) {
        super(message, cause);
    }
}
