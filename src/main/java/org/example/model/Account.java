package org.example.model;

import org.example.exception.InsufficientFundsException;

/**
 * Абстрактный класс банковского счета
 */
public abstract class Account {
    protected double balance;

    /**
     * @param balance - баланс счета
     * @throws IllegalArgumentException в случае отрицательного значения balance
     */
    public Account(double balance) throws IllegalArgumentException {
        this.balance = createAccount(balance);
    }

    /**
     * Метод пополнения баланса счета
     *
     * @param money - сумма средств
     * @throws IllegalArgumentException в случае отрицательного значения суммы средств
     */
    public void depositMoney(double money) throws IllegalArgumentException {
        if (money < 0) {
            throw new IllegalArgumentException("Невозможно внести отрицательную сумму на Ваш баланс счета!");
        } else this.balance += money;
    }

    /**
     * Метод снятия средств со счета
     *
     * @param money - сумма средств
     * @return сумму снятых средств
     * @throws InsufficientFundsException если средств на счете недостаточно
     */
    public double withdrawMoney(double money) throws InsufficientFundsException {
        if (this.balance < money) {
            throw new InsufficientFundsException(
                    String.format("Ошибка операции со средствами на сумму %.3f, при балансе %.3f",
                            money, this.balance));
        } else {
            balance -= money;
            return money;
        }
    }

    /**
     * Метод для корректного создания аккаунта
     *
     * @param balance - баланс при инициализации банковского счета
     * @return баланс
     * @throws IllegalArgumentException в случае отрицательного значения баланса
     */
    protected double createAccount(double balance) throws IllegalArgumentException {
        if (balance < 0) {
            throw new IllegalArgumentException("Невозможно создать аккаунт с отрицательным балансом!");
        } else return balance;
    }

    public double getBalance() {
        return balance;
    }
}
