//package com.company;

import java.math.BigDecimal;

public class SavingsAccountTest {

    public static void main(String[] args) {
        SavingsAccount saver1 = new SavingsAccount("saver1", BigDecimal.valueOf(2000.00));
        SavingsAccount saver2 = new SavingsAccount("saver2", BigDecimal.valueOf(3000.00));

        System.out.println("Two new accounts set up!\n");
        saver1.displayAccountInformation();
        saver2.displayAccountInformation();

        SavingsAccount.modifyInterestRate(4.00);

        saver1.monthsOfInterest(12);
        saver2.monthsOfInterest(12);

        System.out.println("\nNew balances after 12 months of interest:\n");
        saver1.displayAccountInformation();
        saver2.displayAccountInformation();

        SavingsAccount.modifyInterestRate(5.00);

        saver1.monthsOfInterest(1);
        saver2.monthsOfInterest(1);

        System.out.println("\nNew balances after 1 month with new interest rate\n");
        saver1.displayAccountInformation();
        saver2.displayAccountInformation();
    }
}
