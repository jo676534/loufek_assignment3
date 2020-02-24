//package com.company;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class SavingsAccount {

    //Global Variable Declarations
    private static BigDecimal annualInterestRate; //Contains the mathematical version (ex 0.04 rather than 4%)
    private BigDecimal savingsBalance;
    private BigDecimal monthlyInterest;
    private String accountName;

    //Savings Account Constructor
    SavingsAccount(String name, BigDecimal balance) {
        this.accountName = name;
        this.savingsBalance = balance;
        this.monthlyInterest = BigDecimal.valueOf(0.00);

        this.savingsBalance = this.savingsBalance.setScale(2, RoundingMode.DOWN);
        this.monthlyInterest = this.monthlyInterest.setScale(2, RoundingMode.DOWN);
    }

    //Methods
    public static void modifyInterestRate(double rate) {
        MathContext a = new MathContext(4);
        setAnnualInterestRate(BigDecimal.valueOf(rate).divide(BigDecimal.valueOf(100), a));

        System.out.println("\nNew annual interest rate set to: " + (getAnnualInterestRate().multiply(BigDecimal.valueOf(100))) + "%\n");
    }


    public void monthsOfInterest(int months) {
        System.out.println("\nDetermining interest for " + getAccountName() + " over " + months + " month(s) with a " + (getAnnualInterestRate().multiply(BigDecimal.valueOf(100))) + "% yearly interest");
        for (int i = 0; i < months ; i++) {
            calculateMonthlyInterest();
            System.out.println("\nMonth " + (i+1) + " Interest Gained: $" + getMonthlyInterest());
            displayAccountInformation();
        }
    }


    public void calculateMonthlyInterest() {
        MathContext a = new MathContext(4);

        //multiply savings balance by interest rate divided by 12
        setMonthlyInterest(getSavingsBalance().multiply(getAnnualInterestRate()).divide(BigDecimal.valueOf(12), a));

        //interest should then be added to savings balance
        setSavingsBalance(getSavingsBalance().add(getMonthlyInterest()));
    }

    public void displayAccountInformation() {
        System.out.println("Account Name: " + getAccountName());
        System.out.println("Account Balance: $" + getSavingsBalance());
        System.out.println();
    }

    ////////////////////////////////////////////////

    public static BigDecimal getAnnualInterestRate() {
        return annualInterestRate;
    }

    public static void setAnnualInterestRate(BigDecimal annualInterestRate) {
        SavingsAccount.annualInterestRate = annualInterestRate;
    }

    ///////////////////////////////////////////////

    public BigDecimal getSavingsBalance() {
        return savingsBalance.round(new MathContext(6));
    }

    public void setSavingsBalance(BigDecimal savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    //////////////////////////////////////////////////

    public BigDecimal getMonthlyInterest() {
        return monthlyInterest;
    }

    public void setMonthlyInterest(BigDecimal monthlyInterest) {
        this.monthlyInterest = monthlyInterest;
    }

    ///////////////////////////////////////////////////////

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
