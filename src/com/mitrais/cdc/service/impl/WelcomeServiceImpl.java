package com.mitrais.cdc.service.impl;

import com.mitrais.cdc.model.Account;
import com.mitrais.cdc.model.AccountListConstant;
import com.mitrais.cdc.service.MenuService;

import java.util.Scanner;

/**
 * @author Rahmawati Sitti Azizah
 * @version $Id: WelcomeServiceImpl.java, v 0.1 2019-09-20 7:43
 */
public class WelcomeServiceImpl implements MenuService {

    private Account account;
    private boolean loop = true;

    public void display() {
        Scanner scanner = new Scanner(System.in);
        do{
            account = new Account();
            System.out.print("Enter Account Number: ");
            String accountNumber = scanner.nextLine();
            if(isInputCorrect(accountNumber,"Account Number")) {
                account.setAccountNumber(accountNumber);
                System.out.print("Enter PIN: ");
                String pin = scanner.nextLine();
                if(isInputCorrect(pin, "PIN")) {
                    account.setPin(pin);
                    account = AccountListConstant.getAccount(account);
                    if(account!=null){
                        loop = false;
                    }else{
                        System.out.println("Invalid Account Number/PIN if records is not exist.");
                    }
                }
            }
        }while (loop);
    }

    public MenuService process() {
        display();
        TransactionServiceImpl nextScreen = new TransactionServiceImpl();
        nextScreen.setAccount(account);
        return nextScreen;
    }

    private boolean isInputCorrect(String input, String fieldName){
        try {
            Integer.parseInt(input);
            if(input.length()!=6)
                System.out.println(fieldName+" should have 6 digits length");
            else {
                return true;
            }
        }catch (NumberFormatException e){
            System.out.print(fieldName+" should only contains numbers");
        }
        return false;
    }
}
