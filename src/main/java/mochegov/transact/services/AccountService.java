package mochegov.transact.services;

import mochegov.transact.enums.ChangeRestType;
import mochegov.transact.model.Account;
import mochegov.transact.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String accountNumber) {
        return accountRepository.save(new Account(accountNumber));
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public void changeAccountRest(Account account, ChangeRestType changeRestType, BigDecimal sum) {
        if (changeRestType == ChangeRestType.INCREASE) {
            account.setRest(account.getRest().add(sum));
        } else {
            account.setRest(account.getRest().subtract(sum));
        }
        accountRepository.save(account);
    }
}
