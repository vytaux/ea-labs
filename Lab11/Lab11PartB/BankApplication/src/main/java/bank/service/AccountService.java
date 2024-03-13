package bank.service;

import bank.domain.Account;
import bank.dto.request.DepositRequest;
import bank.dto.response.AccountDTO;

import java.util.Collection;

public interface AccountService {

    public AccountDTO createAccount(Long accountNumber, String customerName);

    public AccountDTO getAccount(Long accountNumber);

    public Collection<AccountDTO> getAllAccounts();

    public void deposit (Long accountNumber, DepositRequest depositRequest);

    public void withdraw (Long accountNumber, double amount);

    public void depositEuros (Long accountNumber, DepositRequest depositRequest);

    public void withdrawEuros (Long accountNumber, double amount);

    public void transferFunds(Long fromAccountNumber, Long toAccountNumber, double amount, String description);

}
