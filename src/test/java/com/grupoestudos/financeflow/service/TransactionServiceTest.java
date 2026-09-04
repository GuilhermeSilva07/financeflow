package com.grupoestudos.financeflow.service;

import com.grupoestudos.financeflow.enums.Category;
import com.grupoestudos.financeflow.exception.TransactionNotFoundException;
import com.grupoestudos.financeflow.model.Transaction;
import com.grupoestudos.financeflow.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void deveSalvarTransacaoEPreencherDataDeCriacao() {
        // --- ARRANGE ---
        Transaction transacao = new Transaction();
        transacao.setDescription("Salário");
        transacao.setValue(new BigDecimal("5000"));

        Transaction transacaoSalvaMock = new Transaction();
        transacaoSalvaMock.setId(1L);
        transacaoSalvaMock.setDescription("Salário");

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transacaoSalvaMock);

        // --- ACT ---
        Transaction resultado = transactionService.save(transacao);

        // --- ASSERT ---
        assertNotNull(resultado);
        // Valida se a sua regra de negócio de preencher a data foi executada
        assertNotNull(transacao.getDateCreated(), "O Service deve preencher a data de criação");
        verify(transactionRepository, times(1)).save(transacao);
    }

    @Test
    void deveRetornarTransacaoQuandoIdExistir() {
        // --- ARRANGE ---
        Long id = 1L;
        Transaction transacaoMock = new Transaction();
        transacaoMock.setId(id);

        when(transactionRepository.findById(id)).thenReturn(Optional.of(transacaoMock));

        // --- ACT ---
        Transaction resultado = transactionService.findById(id);

        // --- ASSERT ---
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
    }

    @Test
    void deveLancarExceptionQuandoIdNaoExistir() {
        // --- ARRANGE ---
        Long idInexistente = 99L;
        when(transactionRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // --- ACT & ASSERT ---
        assertThrows(TransactionNotFoundException.class, () -> {
            transactionService.findById(idInexistente);
        });
    }

    @Test
    void deveDeletarTransacaoComSucesso() {
        // --- ARRANGE ---
        Long id = 1L;
        Transaction transacaoMock = new Transaction();
        transacaoMock.setId(id);

        // Ensina o mock a encontrar a transação para o findById interno do seu método delete
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transacaoMock));

        // --- ACT ---
        transactionService.delete(id);

        // --- ASSERT ---
        verify(transactionRepository, times(1)).delete(transacaoMock);
    }

    @Test
    void deveRetornarListaDeTransacoesPorCategoria() {
        // --- ARRANGE ---
        Transaction t1 = new Transaction();
        // Usando o seu Enum corretamente aqui!
        t1.setCategory(Category.FOOD);

        when(transactionRepository.findByCategory(Category.FOOD)).thenReturn(List.of(t1));

        // --- ACT ---
        List<Transaction> resultado = transactionService.findByCategory(Category.FOOD);

        // --- ASSERT ---
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals(Category.FOOD, resultado.get(0).getCategory());
    }
}