package mochegov.transact.services;

import mochegov.transact.dto.ResultDto;
import mochegov.transact.enums.ChangeRestType;
import mochegov.transact.enums.EntryState;
import mochegov.transact.model.Account;
import mochegov.transact.model.Entry;
import mochegov.transact.repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class EntryService {
    private EntryRepository entryRepository;
    private AccountService accountService;

    private boolean testExceptionIsOn;

    @Autowired
    public EntryService(EntryRepository entryRepository,
                        AccountService accountService,
                        @Value("${transact.testException.isOn}") boolean testExceptionIsOn) {
        this.entryRepository = entryRepository;
        this.accountService = accountService;
        this.testExceptionIsOn = testExceptionIsOn;
    }

    public ResultDto createEntry(
            Date date,
            BigDecimal sum,
            Long debitAccountId,
            Long creditAccountId,
            String purpose) {
        Account debitAccount = accountService.getAccountById(debitAccountId);
        if (debitAccount == null) {
            return ResultDto.resultError("Не найден счет дебита по id: " + debitAccountId);
        }

        Account creditAccount = accountService.getAccountById(creditAccountId);
        if (creditAccount == null) {
            return ResultDto.resultError("Не найден счет кредита по id: " + creditAccountId);
        }

        Entry entry = entryRepository.save(new Entry(date, sum, debitAccount, creditAccount, purpose));
        if (entry == null) {
            return ResultDto.resultError("Не удалось создать проводку");
        }

        return ResultDto.resultOk(entry.getId());
    }

    @Transactional
    public ResultDto processEntry(Long id) {
        Entry entry = entryRepository.findById(id).orElse(null);
        if (entry == null) {
            return ResultDto.resultError("Не найдена проводка по id: " + id);
        }

        accountService.changeAccountRest(entry.getDebitAccount(), ChangeRestType.DECREASE, entry.getSum());

        if (testExceptionIsOn) {
            throw new RuntimeException("Внезапный сбой!");
        }

        accountService.changeAccountRest(entry.getCreditAccount(), ChangeRestType.INCREASE, entry.getSum());

        entry.setEntryState(EntryState.PROCESSED);
        entryRepository.save(entry);

        return ResultDto.resultOk(entry.getId());
    }


    public List<Entry> getEntries() {
        return entryRepository.findAll();
    }
}
