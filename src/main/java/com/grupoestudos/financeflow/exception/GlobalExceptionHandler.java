package com.grupoestudos.financeflow.exception;

import com.grupoestudos.financeflow.dto.ApiErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Trata o erro 404 quando um ID não existe no banco
    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleTransactionNotFound(TransactionNotFoundException ex) {
        ApiErrorDTO error = new ApiErrorDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage() // Reaproveita a mensagem configurada na sua Exception
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Fallback de segurança: trata qualquer outro erro inesperado (500) para não vazar a stacktrace no Postman
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDTO> handleGenericException(Exception ex) {
        ApiErrorDTO error = new ApiErrorDTO(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro interno no servidor."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}