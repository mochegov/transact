package mochegov.transact.enums;

public enum EntryState {
    NEW ("Новая"),
    PROCESSED ("Проведена"),
    LIQUIDATED("Ликвидирована");

    private String name;

    EntryState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
