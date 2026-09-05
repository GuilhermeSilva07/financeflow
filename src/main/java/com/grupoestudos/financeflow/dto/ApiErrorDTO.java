package com.grupoestudos.financeflow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorDTO {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private List<String> errors;

    public ApiErrorDTO(LocalDateTime timestamp, Integer status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public ApiErrorDTO(LocalDateTime timestamp, Integer status, String message, List<String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}