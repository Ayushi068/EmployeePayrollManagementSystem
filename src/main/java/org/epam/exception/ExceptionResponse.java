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

}
