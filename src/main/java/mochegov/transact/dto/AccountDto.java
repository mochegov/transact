package mochegov.transact.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDto {
    private Long accountId;
    private String accountNumber;
    private BigDecimal rest;

    public AccountDto() {}

    public AccountDto(Long accountId, String accountNumber, BigDecimal rest) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.rest = rest;
    }
}
