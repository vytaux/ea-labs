package accounts.repository;

import accounts.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void whenFindByAccountHolder_thenReturn1Account() {
        // given
        String accountHolder = "testHolder";
        Account account = new Account();
        // set account properties here
        account.setAccountHolder(accountHolder);
        account.setAccountNumber("123");
        entityManager.persist(account);
        entityManager.flush();

        // when
        Account found = accountRepository.findByAccountHolder(accountHolder);

        // then
        assertNotNull(found);
        assertEquals(accountHolder, found.getAccountHolder());
    }

    @Test
    void whenFindByBalanceGreaterThan100_thenReturn1Account() {
        // given
        Account accountMoreThan100 = new Account();
        accountMoreThan100.setAccountNumber("123");
        accountMoreThan100.setBalance(101);
        entityManager.persist(accountMoreThan100);
        Account accountLessThan100 = new Account();
        accountLessThan100.setAccountNumber("142");
        accountLessThan100.setBalance(5);
        entityManager.persist(accountLessThan100);
        entityManager.flush();

        // when
        List<Account> found = accountRepository.findByBalanceGreaterThan(100.0);

        // then
        assertEquals(1, found.size());
    }

    @Test
    void whenFindByBalanceEqualsZero_thenReturn1Account() {
        // given
        Account accountMoreThan100 = new Account();
        accountMoreThan100.setAccountNumber("123");
        accountMoreThan100.setBalance(101);
        entityManager.persist(accountMoreThan100);
        Account accountZero = new Account();
        accountZero.setAccountNumber("142");
        accountZero.setBalance(0);
        entityManager.persist(accountZero);
        entityManager.flush();

        // when
        List<Account> found = accountRepository.findByBalanceEquals(0.0);

        // then
        assertEquals(1, found.size());
    }
}