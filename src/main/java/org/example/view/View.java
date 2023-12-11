package org.example.view;

import org.example.exception.InsufficientFundsException;
import org.example.presenter.Presenter;

import java.util.Scanner;

public class View {
    Presenter presenter;
    private Scanner scanner;
    private static final String NUMBER_REGEX = "-?(\\d*\\.)?\\d+";

    /**
     * Метод с основной логикой приложения по взаимодействию с пользователем
     */
    public void run() {
        System.out.print("Инициализация начального баланса на вашей ДЕБЕТОВОЙ карте. ");
        double initialDebit = getAmount();
        System.out.print("Инициализация начального баланса на вашей КРЕДИТНОЙ карте. ");
        double initialCredit = getAmount();
        presenter = new Presenter(initialDebit, initialCredit);
        while (true) {
            System.out.println(presenter.getDebit());
            System.out.println(presenter.getCredit());
            System.out.printf("Сумма долга по кредитной карте: %.3f у.е.\n", presenter.getCredit().calculateDebt());
            menu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    try {
                        double amount = getAmount();
                        presenter.withdrawMoneyFromDebit(amount);
                        continue;
                    } catch (InsufficientFundsException e) {
                        throw e;
                    }
                case 2:
                    try {
                        double amount = getAmount();
                        presenter.withdrawMoneyFromCredit(amount);
                        continue;
                    } catch (InsufficientFundsException e) {
                        throw e;
                    }
                case 3:
                    try {
                        double amount = getAmount();
                        presenter.depositMoneyToDebit(amount);
                        continue;
                    } catch (IllegalArgumentException e) {
                        throw e;
                    }
                case 4:
                    try {
                        double amount = getAmount();
                        if (!presenter.depositMoneyToCredit(amount)) {
                            System.out.printf("Сумма, на которую вы пытаетесь пополнить кредитную карту превышает " +
                                    "сумму долга: %.3f\n", presenter.getCredit().calculateDebt());
                        }
                        continue;
                    } catch (IllegalArgumentException e) {
                        throw e;
                    }
                case 5:
                    try {
                        double amount = getAmount();
                        presenter.transferMoney(presenter.getDebit(), presenter.getCredit(), amount);
                        continue;
                    } catch (IllegalArgumentException e) {
                        throw e;
                    }
                case 6:
                    try {
                        double amount = getAmount();
                        presenter.transferMoney(presenter.getCredit(), presenter.getDebit(), amount);
                        continue;
                    } catch (IllegalArgumentException e) {
                        throw e;
                    }
                case 7:
                    System.out.println("Завершение работы программы.");
                    return;
            }
        }
    }

    /**
     * Метод запроса у пользователя суммы средств
     *
     * @return сумму средств
     */
    public double getAmount() {
        String initialBalance = prompt("Введите сумму: ");
        while (!initialBalance.matches(NUMBER_REGEX)) {
            System.out.println("Значение суммы введено не корректно!");
            initialBalance = prompt("Введите сумму: ");
        }
        return Double.parseDouble(initialBalance);
    }

    /**
     * Метод взаимодействия с пользователем с помощью приглашений
     *
     * @param message текстовое приглашение на действие
     * @return введенную пользователем строку
     */
    private String prompt(String message) {
        scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    /**
     * Меню-инструкция
     */
    private void menu() {
        System.out.print("Выберите действие для работы с банковским аккаунтом:\n" +
                "1. Снять деньги с дебетовой карты\n" +
                "2. Снять деньги в кредитной карты\n" +
                "3. Пополнить дебетовую карту\n" +
                "4. Пополнить кредитную карту\n" +
                "5. Перевести деньги с дебетовой карты на кредитную карту\n" +
                "6. Перевести деньги с кредитной карты на дебетовую карту\n" +
                "7. Выход\n");
    }

    /**
     * Метод получения у пользователя варианта действия в виде числа от 1 до 7, в соответствии с меню-инструкцией
     *
     * @return вариант действия
     */
    private int getUserChoice() {
        String StringChoice;
        do {
            StringChoice = scanner.nextLine();
            if (!StringChoice.matches(NUMBER_REGEX) ||
                    Integer.parseInt(StringChoice) <= 0 ||
                    Integer.parseInt(StringChoice) > 7)
                System.out.println("Введен некорректный формат порядкового номера действия. Попробуйте еще раз: ");
        } while (!StringChoice.matches(NUMBER_REGEX) ||
                Integer.parseInt(StringChoice) <= 0 ||
                Integer.parseInt(StringChoice) > 7);
        return Integer.parseInt(StringChoice);
    }
}
