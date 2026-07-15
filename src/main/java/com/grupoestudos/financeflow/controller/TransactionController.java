package com.grupoestudos.financeflow.controller;

import com.grupoestudos.financeflow.dto.TransactionDTO;
import com.grupoestudos.financeflow.enums.Category;
import com.grupoestudos.financeflow.model.Transaction;
import com.grupoestudos.financeflow.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // POST /transactions
    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setDescription(dto.getDescription());
        transaction.setValue(dto.getValue());
        transaction.setType(dto.getType());
        transaction.setCategory(dto.getCategory());

        Transaction saved = transactionService.save(transaction);
        return ResponseEntity.ok(saved);
    }

    // GET /transactions
    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    // GET /transactions/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }

    // GET /transactions/category/{category}
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Transaction>> findByCategory(@PathVariable Category category) {
        return ResponseEntity.ok(transactionService.findByCategory(category));
    }

    // DELETE /transactions/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}