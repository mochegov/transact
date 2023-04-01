package mochegov.transact.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreateEntryDto {
    private Date date;
    private BigDecimal sum;
    private Long debitAccountId;
    private Long creditAccountId;
    private String purpose;

    public CreateEntryDto() {}
}
