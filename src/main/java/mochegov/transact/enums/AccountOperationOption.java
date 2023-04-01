package mochegov.transact.enums;

public enum AccountOperationOption {
    LPC ("Тип продукта"),
    GPC ("Вид продукта"),
    SYMBOL_INCOME ("Символ дохода"),
    TERM_OF_CONTRACT ("Срок договора (контракта)"),
    TERM_UNIT ("Единица измерения срока (YEAR, MONTH, DAY)");
    private String name;

    AccountOperationOption(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
