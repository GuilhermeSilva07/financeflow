package com.grupoestudos.financeflow.dto;

import com.grupoestudos.financeflow.enums.Category;
import com.grupoestudos.financeflow.enums.TransactionType;
import lombok.Data;

// Importante: certifique-se de que os imports começam com "jakarta.validation"
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class TransactionDTO {

    @NotBlank(message = "A descrição não pode estar vazia")
    private String description;

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser maior que zero")
    private BigDecimal value;

    @NotNull(message = "O tipo de transação é obrigatório")
    private TransactionType type;

    @NotNull(message = "A categoria é obrigatória")
    private Category category;

}