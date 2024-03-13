package bank.service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.dto.request.DepositRequest;
import bank.dto.response.AccountDTO;
import bank.integration.jms.JMSSender;
import bank.integration.logging.Logger;
import bank.repository.AccountRepository;
import bank.service.mapper.AccountMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final CurrencyConverter currencyConverter;
	private final JMSSender jmsSender;
	private final Logger logger;

	public AccountServiceImpl(
		AccountRepository accountRepository,
		CurrencyConverter currencyConverter,
		JMSSender jmsSender,
		Logger logger
	) {
		this.accountRepository = accountRepository;
		this.currencyConverter = currencyConverter;
		this.jmsSender = jmsSender;
		this.logger = logger;
	}

	public AccountDTO createAccount(Long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountRepository.save(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return AccountMapper.toDTO(account);
	}

	public void deposit(Long accountNumber, Double amount) {
		Account account = accountRepository
				.findByAccountNumber(accountNumber)
				.orElseThrow();
		account.deposit(amount);
		accountRepository.save(account);
		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public AccountDTO getAccount(Long accountNumber) {
		Account account = accountRepository
				.findByAccountNumber(accountNumber)
				.orElseThrow();
		return AccountMapper.toDTO(account);
	}

	public Collection<AccountDTO> getAllAccounts() {
		Collection<Account> accounts = accountRepository.findAll();
		return AccountMapper.toDTOs(accounts);
	}

	public void withdraw(Long accountNumber, double amount) {
		Account account = accountRepository
				.findByAccountNumber(accountNumber)
				.orElseThrow();
		account.withdraw(amount);
		accountRepository.save(account);
		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void depositEuros(Long accountNumber, Double amount) {
		Account account = accountRepository
				.findByAccountNumber(accountNumber)
				.orElseThrow();
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountRepository.save(account);
		logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public void withdrawEuros(Long accountNumber, double amount) {
		Account account = accountRepository
				.findByAccountNumber(accountNumber)
				.orElseThrow();
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountRepository.save(account);
		logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void transferFunds(Long fromAccountNumber, Long toAccountNumber, double amount, String description) {
		Account fromAccount = accountRepository
				.findByAccountNumber(fromAccountNumber)
				.orElseThrow();
		Account toAccount = accountRepository
				.findByAccountNumber(toAccountNumber)
				.orElseThrow();
		fromAccount.transferFunds(toAccount, amount, description);
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
	}
}
