package mochegov.transact.enums;

public enum Result {
    OK ("Успешно"),
    ERROR ("Ошибка");

    private String name;

    Result(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
