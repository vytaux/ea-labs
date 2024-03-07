package bank.service.mapper;

import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.dto.response.AccountEntryDTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountEntryMapper {

    public static AccountEntryDTO toDTO(AccountEntry accountEntry) {
        return new AccountEntryDTO(
                accountEntry.getId(),
                accountEntry.getDate(),
                accountEntry.getAmount(),
                accountEntry.getDescription(),
                accountEntry.getFromAccountNumber(),
                accountEntry.getFromPersonName()
        );
    }

    public static List<AccountEntryDTO> toDTOs(Collection<AccountEntry> accountEntries) {
        return accountEntries.stream()
                .map(AccountEntryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
