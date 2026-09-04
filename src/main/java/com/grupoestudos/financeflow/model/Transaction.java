package com.grupoestudos.financeflow.model;

import com.grupoestudos.financeflow.enums.Category;
import com.grupoestudos.financeflow.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_value", nullable = false, precision = 15, scale = 2)
    private BigDecimal value;

    @Column(nullable = false, length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;
}
