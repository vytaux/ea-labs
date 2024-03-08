package bank.repository;

import bank.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, Long> {
    Optional<Account> findByAccountNumber(Long accountNumber);
}