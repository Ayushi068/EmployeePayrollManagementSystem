package org.epam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ExceptionResponse {
    private String timestamp;
    private String status;
    private String error;
    private String path;

    public ExceptionResponse(String timestamp, String status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error =error;
        this.path = path;
    }
}
