package com.grupoestudos.financeflow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Oculta o campo "errors" do JSON se ele for nulo!
public class ApiErrorDTO {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private List<String> errors; // Nova lista de erros

    // Construtor para erros simples (mantém os seus métodos atuais funcionando)
    public ApiErrorDTO(LocalDateTime timestamp, Integer status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    // Construtor completo para quando tivermos validações
    public ApiErrorDTO(LocalDateTime timestamp, Integer status, String message, List<String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}