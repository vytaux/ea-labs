package bank;

import bank.domain.Account;
import bank.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class AccountTest {

    @Test
    public void testIncrement() {
        Account account = new Account();
        account.deposit(100.0);

        assertThat(account.getBalance(), closeTo(100.0, 0.01));
    }

    @Test
    public void testDecrement() {
        Account account = new Account();
        account.deposit(100.0);
        account.withdraw(50.0);

        assertThat(account.getBalance(), closeTo(50.0, 0.01));
    }

    @Test
    public void testTransfer() {
        Account account1 = new Account();
        account1.setCustomer(new Customer());
        account1.deposit(100.0);
        Account account2 = new Account();
        account2.setCustomer(new Customer());
        account2.deposit(100.0);

        account1.transferFunds(account2, 50.0, "test");

        assertThat(account1.getBalance(), closeTo(50.0, 0.01));
        assertThat(account2.getBalance(), closeTo(150.0, 0.01));
    }
}
