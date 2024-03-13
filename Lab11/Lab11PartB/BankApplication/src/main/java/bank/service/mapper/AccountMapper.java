package bank.service.mapper;

import bank.domain.Account;
import bank.dto.response.AccountDTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {

    public static AccountDTO toDTO(Account account) {
        return new AccountDTO(
            account.getAccountNumber(),
            account.getBalance(),
            account.getCustomer().getName(),
            AccountEntryMapper.toDTOs(account.getEntryList())
        );
    }

    public static Collection<AccountDTO> toDTOs(Collection<Account> accounts) {
        return accounts.stream()
                .map(AccountMapper::toDTO)
                .collect(Collectors.toList());
    }
}
