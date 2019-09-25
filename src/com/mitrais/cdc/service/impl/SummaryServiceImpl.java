package com.mitrais.cdc.service.impl;

import com.mitrais.cdc.model.Account;
import com.mitrais.cdc.service.MenuService;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * @author Rahmawati Sitti Azizah
 * @version $Id: SummaryServiceImpl.java, v 0.1 2019-09-23 15:28
 */
public class SummaryServiceImpl implements MenuService {

    private Account account;
    private int amount;

    @Override
    public void display() {
        System.out.println("Summary");
        System.out.println("Date :"+ LocalDateTime.now());
        System.out.println("Withdraw : $"+amount);
        System.out.println("Balance : $"+account.getBalance());
        System.out.println("");
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Choose option[2]: ");
    }

    @Override
    public MenuService process() {
        display();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            if(input != null) {
                int choice = Integer.parseInt(input);
                  if(choice ==1){
                      TransactionServiceImpl nextScreen = new TransactionServiceImpl();
                      nextScreen.setAccount(account);
                      return nextScreen;
                  }
            }else{
                TransactionServiceImpl nextScreen = new TransactionServiceImpl();
                return nextScreen;
            }
        }catch (NumberFormatException e){}
        return new WelcomeServiceImpl();
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
