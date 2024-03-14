package bank.service.scheduler;

import bank.domain.Account;
import bank.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Transactional
public class BankStatementPrinter {

    private final AccountRepository accountRepository;

    public BankStatementPrinter(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Scheduled(fixedRate = 20000)
    public void performTask() {
        Collection<Account> accounts = accountRepository.findAll();

        System.out.printf("Found %d accounts\n", accounts.size());
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}