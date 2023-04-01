package mochegov.transact.dto;

import lombok.Getter;
import lombok.Setter;
import mochegov.transact.enums.EntryState;
import mochegov.transact.model.Entry;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class EntryDto {
    private Long entryId;
    private EntryState entryState;
    private Date date;
    private BigDecimal sum;
    private Long debitAccountId;
    private String debitAccountNumber;
    private Long creditAccountId;
    private String creditAccountNumber;
    private String purpose;

    public EntryDto() {}

    public EntryDto(Entry entry) {
        this.entryId = entry.getId();
        this.entryState = entry.getEntryState();
        this.date = entry.getDate();
        this.sum = entry.getSum();
        this.purpose = entry.getPurpose();
        this.debitAccountId = entry.getDebitAccount().getId();
        this.debitAccountNumber = entry.getDebitAccount().getAccountNumber();
        this.creditAccountId = entry.getCreditAccount().getId();
        this.creditAccountNumber = entry.getCreditAccount().getAccountNumber();
    }
}
