package com.mitrais.cdc.model;

/**
 * @author Rahmawati Sitti Azizah
 * @version $Id: TransferInfo.java, v 0.1 2019-09-25 8:24
 */
public class TransferInfo {
    private Account senderAccount;
    private Account destAccount;
    private int amount;
    private String reffNum;

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getDestAccount() {
        return destAccount;
    }

    public void setDestAccount(Account destAccount) {
        this.destAccount = destAccount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReffNum() {
        return reffNum;
    }

    public void setReffNum(String reffNum) {
        this.reffNum = reffNum;
    }
}
