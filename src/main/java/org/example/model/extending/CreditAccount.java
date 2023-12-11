package org.example.model.extending;

import org.example.model.Account;

/**
 * Аккаунт кредитной карты
 * loanAmount - сумма кредита
 */
public class CreditAccount extends Account {
    private final double loanAmount;

    /**
     * @param balance - баланс
     * @throws IllegalArgumentException в случае попытки создать аккаунт с отрицательным балансом
     */
    public CreditAccount(double balance) throws IllegalArgumentException {
        super(balance);
        this.loanAmount = balance;
    }

    /**
     * Метод расчета задолженности по карте
     *
     * @return задолженность (разница между суммой кредита и балансом на карте)
     */
    public double calculateDebt() {
        return this.loanAmount - this.balance;
    }

    @Override
    public String toString() {
        return String.format("Баланс кредитной карты: %.3f у.е.", this.balance);
    }
}
