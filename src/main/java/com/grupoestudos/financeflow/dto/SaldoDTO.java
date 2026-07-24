package com.grupoestudos.financeflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaldoDTO {

    private Double totalReceitas;
    private Double totalDespesas;
    private Double saldo;

}
