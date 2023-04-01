package mochegov.transact.controllers;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import mochegov.transact.dto.*;
import mochegov.transact.enums.Result;
import mochegov.transact.model.Account;
import mochegov.transact.model.Entry;
import mochegov.transact.services.AccountService;
import mochegov.transact.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(value="Transaction test API", description="Отладка транзакций")
public class MainController {

    private AccountService accountService;
    private EntryService entryService;

    @Autowired
    public MainController(AccountService accountService, EntryService entryService) {
        this.accountService = accountService;
        this.entryService = entryService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        log.info("Получение всех счетов");

        List<Account> accounts = accountService.getAccounts();
        List<AccountDto> accountDtoList =
        accounts.stream()
                .map(account -> new AccountDto(account.getId(), account.getAccountNumber(), account.getRest()))
                .sorted(Comparator.comparing(AccountDto::getAccountNumber))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(accountDtoList);
    }

    @GetMapping("/entries")
    public ResponseEntity<List<EntryDto>> getEntries() {
        log.info("Получение всех проводок");

        List<Entry> entries = entryService.getEntries();
        List<EntryDto> entryDtoList =
                entries.stream()
                        .map(entry -> new EntryDto(entry))
                        .sorted(Comparator.comparing(EntryDto::getEntryId))
                        .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(entryDtoList);
    }

    @PostMapping("/entries/create")
    public ResponseEntity<ResultDto> createEntry(@RequestBody CreateEntryDto requestDto) {
        log.info("Создание новой проводки...");
        log.info(requestDto.toString());

        ResultDto resultDto = entryService.createEntry(
                requestDto.getDate(),
                requestDto.getSum(),
                requestDto.getDebitAccountId(),
                requestDto.getCreditAccountId(),
                requestDto.getPurpose()
                );

        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

    @PostMapping("/entries/process/{id}")
    public ResponseEntity<ResultDto> processEntry(@PathVariable(name = "id") Long id) {
        log.info("Проводка документа id: " + id);

        ResultDto resultDto;
        try {
            resultDto = entryService.processEntry(id);
        } catch (RuntimeException exception) {
            resultDto = ResultDto.resultError(exception.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

    @PostMapping("/accounts/operation")
    public ResponseEntity<ResultDto> processAccountOperation(@RequestBody AccountOpeningRequestDto requestDto) {
        ResultDto resultDto = new ResultDto(Result.OK, 1L, "");
        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "Acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }
}
