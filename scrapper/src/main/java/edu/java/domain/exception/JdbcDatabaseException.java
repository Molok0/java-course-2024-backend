package edu.java.domain.exception;

public class JdbcDatabaseException extends RuntimeException {
    public JdbcDatabaseException(String message) {
        super(message);
    }
}
