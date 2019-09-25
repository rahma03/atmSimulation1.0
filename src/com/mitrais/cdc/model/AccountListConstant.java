package com.mitrais.cdc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rahmawati Sitti Azizah
 * @version $Id: AccountListConstant.java, v 0.1 2019-09-23 13:00
 */
public class AccountListConstant {
    public static final Map<String, Account> accountList;

    static{
        accountList = new HashMap<String, Account>();
        accountList.put("112233", new Account("John Doe", "012108", 100, "112233"));
        accountList.put("112244", new Account("Jane Doe", "932012", 30, "112244"));
    }

    public static void updateAccountList(Account account){
        accountList.put(account.getAccountNumber(), account);
    }
    public static Account getAccount(Account account){
        Account accountExist = accountList.get(account.getAccountNumber());
        if(accountExist!= null && accountExist.getPin().equals(account.getPin()))
            return accountExist;
        return null;
    }

    public static Account getAccount(String accountNumber){
        Account accountExist = accountList.get(accountNumber);
        return accountExist;
    }
}
