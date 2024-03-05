package bank.repository;

import bank.domain.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void saveAccount(Account account) {
		entityManager.persist(account);
	}

	public void updateAccount(Account account) {
		Account accountexist = loadAccount(account.getAccountNumber());
		if (accountexist != null) {
			entityManager.merge(account);
		}
	}

	public Account loadAccount(long accountNumber) {
		return entityManager
				.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber", Account.class)
				.setParameter("accountNumber", accountNumber)
				.getSingleResult();
	}

	public Collection<Account> getAccounts() {
		return entityManager
				.createQuery("SELECT a FROM Account a", Account.class)
				.getResultList();
	}
}
