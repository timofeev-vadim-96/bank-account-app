package org.example.presenter;

import org.example.exception.InsufficientFundsException;
import org.example.model.Account;
import org.example.model.extending.CreditAccount;
import org.example.model.extending.DebitAccount;
import org.example.model.implementing.Transaction;

/**
 * Презентер или обобщающий индивидуальный аккаунт, содержащий и дебет, и кредит.
 */
public class Presenter {
    CreditAccount credit;
    DebitAccount debit;

    /**
     * @param debitBalance  баланс счета на дебетовой карте
     * @param creditBalance баланс счета на кредитной карте
     * @throws IllegalArgumentException в случае отрицательных значений балансов
     */
    public Presenter(double debitBalance, double creditBalance) throws IllegalArgumentException {
        debit = new DebitAccount(debitBalance);
        credit = new CreditAccount(creditBalance);
    }

    /**
     * Метод пополнения кредитной кртаы
     *
     * @param money - сумма средств
     * @return false, если предпринята попытка по внесению средств в количестве, большем задолженности по кредиту
     * @throws IllegalArgumentException
     */
    public boolean depositMoneyToCredit(double money) throws IllegalArgumentException {
        if (money > credit.calculateDebt()) return false;
        else {
            credit.depositMoney(money);
            return true;
        }
    }

    public void depositMoneyToDebit(double money) throws IllegalArgumentException {
        debit.depositMoney(money);
    }

    public void transferMoney(Account sender, Account recipient, double money) throws IllegalArgumentException {
        Transaction transaction = new Transaction(sender, recipient, money);
    }

    public void withdrawMoneyFromDebit(double money) throws InsufficientFundsException {
        debit.withdrawMoney(money);
    }

    public void withdrawMoneyFromCredit(double money) throws InsufficientFundsException {
        credit.withdrawMoney(money);
    }

    public CreditAccount getCredit() {
        return credit;
    }

    public DebitAccount getDebit() {
        return debit;
    }
}
