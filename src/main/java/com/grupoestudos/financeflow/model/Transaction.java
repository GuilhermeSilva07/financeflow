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

    // precision = total de dígitos, scale = casas decimais.
    // Sem isso, o Hibernate usa um padrão genérico que pode
    // truncar ou arredondar valores grandes de forma inesperada.
    // O nome da coluna foi ajustado para evitar conflito com a palavra reservada "value"
    // do H2 durante a criação automática da tabela.
    @Column(name = "transaction_value", nullable = false, precision = 15, scale = 2)
    private BigDecimal value;

    // length define o tamanho da coluna VARCHAR no banco.
    // Sem isso, o padrão é 255 — pode ser ok, mas é melhor decidir você.
    @Column(nullable = false, length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    // updatable = false: depois que a transação é criada,
    // a data de criação nunca deve mudar, mesmo que alguém
    // tente atualizar o registro no futuro.
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    // Campo futuro: toda vez que o registro for atualizado, esse valor muda.
    // private LocalDateTime dateUpdated;

}
