package mochegov.transact.enums;

public enum Currency {
    RUR("810", "Рубли"),
    USD("840", "Доллар США"),
    EUR("978", "Евро"),
    CNY("156", "Юань"),
    JPY("392", "Иена"),
    BYN("933", "Белорусский рубль");

    private String code;
    private String name;

    private Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
