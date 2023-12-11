package org.example.model.implementing;

import org.example.model.Account;
import org.example.model.Transmittable;

/**
 * Транзакция между двумя счетами
 */
public class Transaction implements Transmittable {
    Account sender;
    Account recipient;
    double money;

    /**
     * @param sender    счет отправителя
     * @param recipient счет получателя
     * @param money     сумма средств
     * @throws IllegalArgumentException                         в случае отрицательного значения суммы для перевода
     * @throws org.example.exception.InsufficientFundsException в случае нехватки средств отправителя
     */
    public Transaction(Account sender, Account recipient, double money) throws IllegalArgumentException {
        this.sender = sender;
        this.recipient = recipient;
        this.money = money;
        transferTransaction();
    }

    /**
     * Метод совершения транзакции между двумя счетами
     *
     * @throws IllegalArgumentException                         в случае отрицательного значения суммы для перевода
     * @throws org.example.exception.InsufficientFundsException в случае нехватки средств отправителя
     */
    @Override
    public void transferTransaction() throws IllegalArgumentException {
        this.recipient.depositMoney(this.sender.withdrawMoney(this.money));
    }
}
