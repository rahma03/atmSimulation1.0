package com.mitrais.cdc.service.impl;

import com.mitrais.cdc.model.Account;
import com.mitrais.cdc.model.AccountListConstant;
import com.mitrais.cdc.model.TransferInfo;
import com.mitrais.cdc.service.MenuService;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Rahmawati Sitti Azizah
 * @version $Id: SummaryTransferServiceImpl.java, v 0.1 2019-09-25 7:56
 */
public class SummaryTransferServiceImpl implements MenuService {
    private TransferInfo transferInfo;

    @Override
    public void display() {
        System.out.println("Fund Transfer Summary");
        System.out.println("Destination Account : "+transferInfo.getDestAccount().getAccountNumber());
        System.out.println("Transfer amount : $"+transferInfo.getAmount());
        System.out.println("Reference Number : "+transferInfo.getReffNum());
        System.out.println("Balance : "+transferInfo.getSenderAccount().getBalance());
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Choose Option[2]:");
    }

    @Override
    public MenuService process() {
        display();
        Scanner scanner = new Scanner(System.in);
        int choice = 2;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {}
        switch (choice){
            case 1 :TransactionServiceImpl transactionScreen = new TransactionServiceImpl();
                    transactionScreen.setAccount(transferInfo.getSenderAccount());
                    return transactionScreen;
            default: return new WelcomeServiceImpl();
        }
    }

    public void setTransferInfo(TransferInfo transferInfo) {
        this.transferInfo = transferInfo;
    }
}
