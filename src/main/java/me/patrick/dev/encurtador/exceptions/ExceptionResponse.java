package me.patrick.dev.encurtador.exceptions;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
 

@Data
@Builder
public class ExceptionResponse {
    
    private final boolean error;
    private final String message;
    private final String details;
    private final LocalDateTime timestamp = LocalDateTime.now();

}
