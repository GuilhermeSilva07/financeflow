package com.grupoestudos.financeflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiErrorDTO {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
}