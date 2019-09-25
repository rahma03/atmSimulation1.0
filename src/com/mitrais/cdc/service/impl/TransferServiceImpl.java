package com.mitrais.cdc.service.impl;

import com.mitrais.cdc.model.Account;
import com.mitrais.cdc.model.AccountListConstant;
import com.mitrais.cdc.model.TransferInfo;
import com.mitrais.cdc.service.MenuService;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Rahmawati Sitti Azizah
 * @version $Id: TransferServiceImpl.java, v 0.1 2019-09-23 8:27
 */
public class TransferServiceImpl implements MenuService {

    private Account account;
    private Account destAccount;
    private int amountInt;
    private String reffNum;
    private boolean abort = false;


    @Override
    public void display() {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        do{
            System.out.println("Please enter destination account and press enter to continue or " +
                    "press enter to go back to Transaction:");
            String accountNumber = scanner.nextLine();
            if(accountNumber.length()!=0){
                if(isAccountCorrect(accountNumber)) {
                    while (loop) {
                        System.out.println("Please enter transfer amount and press enter to continue " +
                                "or press enter to go back to Transaction: ");
                        String amount = scanner.nextLine();
                        if(amount.length()!=0){
                            if (isAmountCorrect(amount)) {
                                amountInt = Integer.parseInt(amount);
                                int reff = new Random().nextInt(999999);
                                reffNum = String.valueOf(reff);
                                while(reffNum.length()<6)
                                    reffNum = "0"+reffNum;
                                System.out.println("Reference Number: "+reffNum);
                                System.out.print("press enter to continue");
                                if(scanner.nextLine().length()>0){
                                    System.out.println("Invalid Reference Number");
                                }else{
                                    System.out.println("Transfer Confirmation");
                                    System.out.println("Destination Account : "+ destAccount.getAccountNumber());
                                    System.out.println("Transfer amount : $"+amount);
                                    System.out.println("Reference Number : "+reffNum);
                                    System.out.println("1. Confirm Trx");
                                    System.out.println("2. Cancel Trx");
                                    System.out.print("Choose Option[2]:");
                                    loop = false;
                                }
                            }
                        }else{
                            loop = false;
                            abort= true;
                        }
                    }
                }
            }else{
                loop = false;
                abort= true;
            }
        }while (loop);

    }

    private boolean isAccountCorrect(String input){
        try {
            Integer.parseInt(input);
            destAccount = new Account();
            destAccount.setAccountNumber(input);
            destAccount = AccountListConstant.getAccount(input);
            if(destAccount !=null)
                return true;
            System.out.println("Invalid account");
        }catch (NumberFormatException e){
            System.out.println("Invalid account");
        }
        return false;
    }

    private boolean isAmountCorrect(String input){
        try {
            int amount = Integer.parseInt(input);
            if(amount>1000)
                System.out.print("Maximum amount to withdraw is $1000");
            else if(amount<1)
                System.out.print("Minimum amount to withdraw is $1");
            else
                return true;
        }catch (NumberFormatException e){
            System.out.println("Invalid amount");
        }
        return false;
    }

    @Override
    public MenuService process() {
        display();
        if(abort){
            TransactionServiceImpl nextScreen = new TransactionServiceImpl();
            nextScreen.setAccount(account);
            return nextScreen;
        }
        Scanner scanner = new Scanner(System.in);
        int choice = 2;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {}
        switch (choice){
            case 1 :account.setBalance(account.getBalance()-amountInt);
                destAccount.setBalance(destAccount.getBalance()+amountInt);
                AccountListConstant.updateAccountList(account);
                AccountListConstant.updateAccountList(destAccount);
                SummaryTransferServiceImpl summaryScreen = new SummaryTransferServiceImpl();
                TransferInfo transferInfo = new TransferInfo();
                transferInfo.setSenderAccount(account);
                transferInfo.setDestAccount(destAccount);
                transferInfo.setAmount(amountInt);
                transferInfo.setReffNum(reffNum);
                summaryScreen.setTransferInfo(transferInfo);
                return summaryScreen;
            default:TransactionServiceImpl nextScreen = new TransactionServiceImpl();
                nextScreen.setAccount(account);
                return nextScreen;
        }
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
