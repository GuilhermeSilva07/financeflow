package com.grupoestudos.financeflow.controller;

import com.grupoestudos.financeflow.dto.BalanceDTO;
import com.grupoestudos.financeflow.dto.TransactionDTO;
import com.grupoestudos.financeflow.enums.Category;
import com.grupoestudos.financeflow.model.Transaction;
import com.grupoestudos.financeflow.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(summary = "Cria uma nova transação")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Transação criada"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<Transaction> create(@Valid @RequestBody TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setDescription(dto.getDescription());
        transaction.setValue(dto.getValue());
        transaction.setType(dto.getType());
        transaction.setCategory(dto.getCategory());

        Transaction saved = transactionService.save(transaction);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @Operation(summary = "Lista todas as transações")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @Operation(summary = "Busca transação por id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Encontrada"),
        @ApiResponse(responseCode = "404", description = "Não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }

    @Operation(summary = "Busca transações por categoria")
    @ApiResponse(responseCode = "200", description = "Lista retornada")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Transaction>> findByCategory(@PathVariable Category category) {
        return ResponseEntity.ok(transactionService.findByCategory(category));
    }

    @Operation(summary = "Deleta uma transação por id")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza uma transação existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Não encontrada"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@PathVariable Long id, @Valid @RequestBody TransactionDTO dto) {
        Transaction updated = transactionService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Calcula e retorna o saldo")
    @ApiResponse(responseCode = "200", description = "Saldo calculado")
    @GetMapping("/balance")
    public ResponseEntity<BalanceDTO> getBalance() {
        BalanceDTO balance = transactionService.calculateBalance();
        return ResponseEntity.ok(balance);
    }
}