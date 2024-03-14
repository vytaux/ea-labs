package bank.controller;

import bank.dto.request.DepositRequest;
import bank.dto.request.TransferRequest;
import bank.dto.request.WithdrawRequest;
import bank.dto.response.AccountDTO;
import bank.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    // POST /accounts -> return success
    @PostMapping
    public AccountDTO createAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.createAccount(accountDTO.getAccountNumber(), accountDTO.getCustomerName());
    }

    // GET /accounts/accountNumber -> return success {account}
    @GetMapping("/{accountNumber}")
    public AccountDTO getAccount(@PathVariable Long accountNumber) {
        return accountService.getAccount(accountNumber);
    }

    // POST /accounts/{accountNumber}/deposit -> return success
    @PostMapping("/{accountNumber}/deposit")
    public void deposit(@PathVariable Long accountNumber, @RequestBody DepositRequest depositRequest) {
        accountService.deposit(accountNumber, depositRequest.getAmount());
    }

    // POST /accounts/accountNumber/depositEuros -> return success
    @PostMapping("/{accountNumber}/depositEuros")
    public void depositEuros(@PathVariable Long accountNumber, @RequestBody DepositRequest depositRequest) {
        accountService.depositEuros(accountNumber, depositRequest.getAmount());
    }

    // POST /accounts/accountNumber/withdraw -> return success
    @PostMapping("/{accountNumber}/withdraw")
    public void withdraw(@PathVariable Long accountNumber, @RequestBody WithdrawRequest withdrawRequest) {
        accountService.withdraw(accountNumber, withdrawRequest.getAmount());
    }

    // POST /accounts/accountNumber/withdrawEuros -> return success
    @PostMapping("/{accountNumber}/withdrawEuros")
    public void withdrawEuros(@PathVariable Long accountNumber, @RequestBody WithdrawRequest withdrawRequest) {
        accountService.withdrawEuros(accountNumber, withdrawRequest.getAmount());
    }

    // POST /accounts/accountNumber/transfer -> return success
    @PostMapping("/{fromAccountNumber}/transfer")
    public void transferFunds(
            @PathVariable Long fromAccountNumber,
            @RequestBody TransferRequest transferRequest
    ) {
        accountService.transferFunds(
                fromAccountNumber,
                transferRequest.getToAccountNumber(),
                transferRequest.getAmount(),
                transferRequest.getDescription()
        );
    }

    // GET /accounts -> return success [{account}]
    @GetMapping
    public Collection<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
