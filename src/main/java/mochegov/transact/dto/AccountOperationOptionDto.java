package mochegov.transact.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import mochegov.transact.enums.AccountOperationOption;

@Data
@Schema(title = "Опции операций со счетами", description = "Опции операций со счетами")
public class AccountOperationOptionDto {
    @Schema(title = "Опция")
    private AccountOperationOption option;
    @Schema(title = "Значение опции")
    private String value;
}
