package za.co.lindaring.gay.exception.handler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionHandlerResponse {
    private int status;
    private String error;
    private String message;
    private Class exception;
}
