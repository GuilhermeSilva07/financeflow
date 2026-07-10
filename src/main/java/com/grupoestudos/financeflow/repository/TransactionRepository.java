package com.grupoestudos.financeflow.repository;

import com.grupoestudos.financeflow.enums.Category;
import com.grupoestudos.financeflow.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCategory(Category category);

}