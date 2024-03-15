package accounts.service;

import accounts.domain.Account;
import accounts.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
class AccountServiceTest {

    @TestConfiguration
    static class AccountServiceTestContextConfiguration {
        @Bean
        public AccountService accountService() {
            return new AccountService();
        }
    }

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    void whenGetAccount_thenFindExistingAccount() {
        // given
        String accountNumber = "123";
        Account account = new Account(accountNumber, 100, "holder1");
        when(accountRepository.findById(accountNumber)).thenReturn(Optional.of(account));

        // when
        AccountResponse result = accountService.getAccount(accountNumber);

        // then
        assertNotNull(result);
        assertEquals(accountNumber, result.getAccountNumber());
        verify(accountRepository, times(1)).findById(accountNumber);
    }

    @Test
    void whenCreateAccount_thenSaveAndReturnAccount() {
        // given
        String accountNumber = "123";
        double amount = 100;
        String accountHolder = "holder1";
        Account account = new Account(accountNumber, amount, accountHolder);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // when
        accountService.createAccount(accountNumber, amount, accountHolder);

        // then
        verify(accountRepository, times(1)).save(any(Account.class));
    }
}