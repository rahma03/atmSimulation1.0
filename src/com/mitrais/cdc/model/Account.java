package com.mitrais.cdc.model;

/**
 * @author Rahmawati Sitti Azizah
 * @version $Id: Account.java, v 0.1 2019-09-19 14:15
 */
public class Account {

    private String name;
    private String pin;
    private float balance;
    private String accountNumber;

    public Account(){};

    public Account(String name, String pin, float balance, String accountNumber) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
