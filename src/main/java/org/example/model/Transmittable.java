package org.example.model;

import org.example.exception.InsufficientFundsException;

/**
 * Интерфейс транзакции
 */
public interface Transmittable {
    public void transferTransaction() throws InsufficientFundsException, IllegalArgumentException;
}
