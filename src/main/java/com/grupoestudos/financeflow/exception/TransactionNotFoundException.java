package com.grupoestudos.financeflow.exception;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(Long id){
        super("Transação não encontrada com o id: " + id);
    }
}
