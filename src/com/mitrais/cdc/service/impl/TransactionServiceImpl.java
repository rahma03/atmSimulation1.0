package com.mitrais.cdc.service.impl;

import com.mitrais.cdc.model.Account;
import com.mitrais.cdc.service.MenuService;

import java.util.Scanner;

/**
 * @author Rahmawati Sitti Azizah
 * @version $Id: TransactionServiceImpl.java, v 0.1 2019-09-20 10:38
 */
public class TransactionServiceImpl implements MenuService {
    private Account account;

    @Override
    public void display() {
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");
        System.out.print("Please choose option[3]: ");
    }

    @Override
    public MenuService process() {
        display();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int choice = 3;
        try {
            if(input != null)
                choice = Integer.parseInt(input);
        }catch (NumberFormatException e){}

        switch (choice){
            case 1 : WithdrawnServiceImpl withdrawnScreen = new WithdrawnServiceImpl();
                withdrawnScreen.setAccount(account);
                return withdrawnScreen;

            case 2 : TransferServiceImpl transferScreen = new TransferServiceImpl();
                transferScreen.setAccount(account);
                return  transferScreen;

            default: break;
        }
        return new WelcomeServiceImpl();
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
