package mochegov.transact.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import mochegov.transact.enums.Branch;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

@Data
@Schema(title = "Запрос на открытие счета", description = "Запрос на открытие (резервирование счета)")
public class AccountOpeningRequestDto {
    @Schema(title = "Филиал")
    private Branch branch;
    @Schema(title = "Валюта")
    private Currency currency;
    @Schema(title = "Код клиента")
    private String clientCode;
    @Schema(title = "Дата открытия")
    private Date dateOpen;
    @Schema(title = "Наименование счета")
    private String nameAccount;
    @Schema(title = "Номер балансового счета второго порядка")
    private String balanceTwo;
    private List<AccountOperationOptionDto> options = new ArrayList<>();
}
