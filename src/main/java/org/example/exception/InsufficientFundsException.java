package org.example.exception;

/**
 * Исключение, возникающее при нехватке средств на счету для совершения действия со счетом
 */
public class InsufficientFundsException extends IllegalArgumentException {

    public InsufficientFundsException(String text) {
        super(text);
    }
}
