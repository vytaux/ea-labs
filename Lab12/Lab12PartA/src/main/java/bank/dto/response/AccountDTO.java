package bank.dto.response;

import bank.domain.AccountEntry;

import java.util.ArrayList;
import java.util.List;

public class AccountDTO {

    private long accountNumber;
    private double balance;
    private String customerName;
    private List<AccountEntryDTO> entryList = new ArrayList<>();

    public AccountDTO() {
    }

    public AccountDTO(long accountNumber, double balance, String customerName, List<AccountEntryDTO> entryList) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerName = customerName;
        this.entryList = entryList;
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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<AccountEntryDTO> getEntryList() {
        return entryList;
    }
}
