package com.grupoestudos.financeflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BalanceDTO {
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal balance;
}