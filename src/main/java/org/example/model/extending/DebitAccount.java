package org.example.model.extending;

import org.example.model.Account;

/**
 * Аккаунт дебетовой карты
 */
public class DebitAccount extends Account {
    public DebitAccount(double balance) throws IllegalArgumentException {
        super(balance);
    }

    @Override
    public String toString() {
        return String.format("Баланс дебетовой карты: %.3f у.е.", this.balance);
    }
}
