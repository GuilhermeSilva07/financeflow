package com.grupoestudos.financeflow.dto;

import com.grupoestudos.financeflow.enums.Category;
import com.grupoestudos.financeflow.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;

// Os campos deste DTO se parecem com os de Transaction de propósito,
// mas representam coisas diferentes: isso é o "contrato" do que
// a API aceita receber, não a estrutura da tabela no banco.
// Por isso não tem id nem dateCreated — quem preenche esses
// dois é o banco e a Service, nunca o cliente da API.

// @Data (Lombok): gera automaticamente, em tempo de compilação,
// getters, setters, toString(), equals() e hashCode() para todos
// os campos abaixo — sem isso, cada um desses métodos precisaria
// ser escrito manualmente.
@Data
public class TransactionDTO {

    // Descrição da transação (ex: "Almoço", "Salário").
    // Texto livre, informado pelo cliente da API.
    private String description;

    // Valor da transação em reais.
    // Usa BigDecimal (e não Double) para evitar erros de
    // arredondamento em valores monetários.
    private BigDecimal value;

    // Tipo da transação: RECEITA (INCOME) ou DESPESA (EXPENSE).
    // Como é um enum, o Spring já valida que só um desses dois
    // valores pode ser aceito.
    private TransactionType type;

    // Categoria da transação (ex: FOOD, TRANSPORT, SALARY).
    // Também é um enum, então valores inválidos são rejeitados
    // automaticamente pelo Spring.
    private Category category;

    // OBS: não há campo "id" nem "dateCreated" aqui de propósito —
    // esses valores são controlados pelo banco e pela Service,
    // nunca pelo cliente da API.
}