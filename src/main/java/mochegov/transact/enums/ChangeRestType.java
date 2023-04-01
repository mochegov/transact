package mochegov.transact.enums;

public enum ChangeRestType {
    INCREASE ("Увеличение"),
    DECREASE ("Уменьшение");

    private String name;

    ChangeRestType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
