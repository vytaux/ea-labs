package client.dto;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private long accountNumber;
    private double balance;
    private String customerName;
    private List<AccountEntry> entryList = new ArrayList<>();

    public Account() {
    }

    public Account(long accountNumber, String customerName) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<AccountEntry> getEntryList() {
        return entryList;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", customerName='" + customerName + '\'' +
                ", entryList=" + entryList +
                '}';
    }
}
