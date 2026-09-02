package com.grupoestudos.financeflow.service;

import com.grupoestudos.financeflow.dto.SaldoDTO;
import com.grupoestudos.financeflow.dto.TransactionDTO;
import com.grupoestudos.financeflow.enums.Category;
import com.grupoestudos.financeflow.enums.TransactionType;
import com.grupoestudos.financeflow.exception.TransactionNotFoundException;
import com.grupoestudos.financeflow.model.Transaction;
import com.grupoestudos.financeflow.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public Transaction save (Transaction transaction) {
        transaction.setDateCreated(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
    }

    public List<Transaction> findByCategory(Category category){
        return transactionRepository.findByCategory(category);
    }

    public void delete(Long id) {
        Transaction transaction = findById(id);
        transactionRepository.delete(transaction);
    }

    public Transaction update(Long id, TransactionDTO dto) {
        Transaction transaction = findById(id);
        transaction.setDescription(dto.getDescription());
        transaction.setValue(dto.getValue());
        transaction.setType(dto.getType());
        transaction.setCategory(dto.getCategory());
        return transactionRepository.save(transaction);
    }

    //foi utilizado a "expressão lambda", bora estudar ela, além do stream
    public SaldoDTO calcularSaldo() {
        List<Transaction> transacoes = findAll();

        double totalReceitas = transacoes.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .mapToDouble(t -> t.getValue().doubleValue())
                .sum();

        double totalDespesas = transacoes.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(t -> t.getValue().doubleValue())
                .sum();

        double saldo = totalReceitas - totalDespesas;

        return new SaldoDTO(totalReceitas, totalDespesas, saldo);
    }
}
