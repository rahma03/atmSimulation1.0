package com.mitrais.cdc.service.impl;

import com.mitrais.cdc.model.Account;
import com.mitrais.cdc.model.AccountListConstant;
import com.mitrais.cdc.service.MenuService;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Rahmawati Sitti Azizah
 * @version $Id: WithdrawnServiceImpl.java, v 0.1 2019-09-20 14:42
 */
public class WithdrawnServiceImpl implements MenuService{
    private Account account;

    @Override
    public void display() {
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");
        System.out.print("Please choose option[5]: ");
    }

    @Override
    public MenuService process() {
        display();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            if(input != null) {
                int choice = Integer.parseInt(input);
                boolean  success = false;
                int amount = 0;
                switch (choice){
                    case 1 :amount = 10;
                            if(withdrawn(amount)){
                                success = true;
                            }
                            break;
                    case 2 :amount = 50;
                            if(withdrawn(50)){
                                success = true;
                            }
                            break;
                    case 3 :amount = 100;
                            if(withdrawn(100)){
                                success = true;
                            }
                            break;
                    case 4 : return displayOtherWithdrawn();

                    default : TransactionServiceImpl nextScreen = new TransactionServiceImpl();
                            return nextScreen;

                }
                if(success){
                    SummaryServiceImpl nextScreen = new SummaryServiceImpl();
                    nextScreen.setAccount(account);
                    nextScreen.setAmount(amount);
                    return nextScreen;
                }else{
                    WithdrawnServiceImpl nextScreen = new WithdrawnServiceImpl();
                    nextScreen.setAccount(account);
                    return nextScreen;
                }
            }else{
                TransactionServiceImpl nextScreen = new TransactionServiceImpl();
                return nextScreen;
            }
        }catch (NumberFormatException e){}

        TransactionServiceImpl nextScreen = new TransactionServiceImpl();
        nextScreen.setAccount(account);
        return nextScreen;
    }

    public boolean withdrawn(float amount) {
        if(amount>1000){
            System.out.println("Maximum amount to withdraw is $1000");
            return false;
        }else if(amount%10!=0){
            System.out.println("Invalid Amount");
            return false;
        }else if(account.getBalance()<amount){
            System.out.println("Insufficient balance $"+amount);
            return false;
        }else{
            account.setBalance(account.getBalance()-amount);
            AccountListConstant.updateAccountList(account);
            return true;
        }
    }

    public MenuService displayOtherWithdrawn(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("Other Withdraw");
            System.out.print("Enter amount to withdraw: ");
            try{
                int amount = scanner.nextInt();
                if(withdrawn(amount)){
                    SummaryServiceImpl nextScreen = new SummaryServiceImpl();
                    nextScreen.setAccount(account);
                    nextScreen.setAmount(amount);
                    return nextScreen;
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid Amount");
            }
        }while(choice == 0);
        return null;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
