package com.grupoestudos.financeflow.controller;

import com.grupoestudos.financeflow.dto.BalanceDTO;
import com.grupoestudos.financeflow.dto.SaldoDTO;
import com.grupoestudos.financeflow.dto.TransactionDTO;
import com.grupoestudos.financeflow.enums.Category;
import com.grupoestudos.financeflow.model.Transaction;
import com.grupoestudos.financeflow.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid; // 1. NOVO IMPORT: pacote jakarta
import java.net.URI;
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
    // 2. ALTERAÇÃO: Adicionado o @Valid antes do @RequestBody
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

    // PUT /transactions/{id}
    @PutMapping("/{id}")
    // 3. ALTERAÇÃO: Adicionado o @Valid antes do @RequestBody também na atualização
    public ResponseEntity<Transaction> update(@PathVariable Long id, @Valid @RequestBody TransactionDTO dto) {
        Transaction updated = transactionService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/saldo")
    public ResponseEntity<SaldoDTO> getSaldo() {
        SaldoDTO saldoCalculado = transactionService.calcularSaldo();
        return ResponseEntity.ok(saldoCalculado);
    }

    @GetMapping("/balance")
    public ResponseEntity<BalanceDTO> getBalance() {
        BalanceDTO balance = transactionService.calculateBalance();
        return ResponseEntity.ok(balance);
    }
}