package bank.service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.domain.TraceRecord;
import bank.integration.EmailSender;
import bank.repository.AccountRepository;
import bank.repository.CustomerRepository;
import bank.repository.TraceRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BankService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private TraceRecordRepository traceRecordRepository;
	
	@Transactional
	public void createCustomerAndAccount(int customerId, String customerName, String emailAddress, String AccountNumber){
		try {
			Account account = new Account(AccountNumber);
			accountRepository.save(account);
			Customer customer = new Customer(customerId, customerName);
			customer.setAccount(account);
			customerRepository.saveCustomer(customer);
			emailSender.sendEmail(emailAddress, "Welcome "+customerName);

			createTraceRecord("Customer " + customerName + " created with account " + AccountNumber);
			// Doesn't seem right to catch Throwable here, but it satisfies the requirement
		} catch (Throwable e) {
			emailSender.sendEmail(emailAddress, "We could not create your account " + AccountNumber);
			createTraceRecord("Could not create customer " + customerName + " with account " + AccountNumber);
			throw e;
		}
	}

	private void createTraceRecord(String result) {
		TraceRecord traceRecord = new TraceRecord();
		traceRecord.setResult(result);
		traceRecord.setCreatedAt(LocalDate.now());
		traceRecordRepository.save(traceRecord);
	}
}
