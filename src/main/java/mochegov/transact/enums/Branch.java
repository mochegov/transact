package mochegov.transact.enums;

public enum Branch {
    MSK("044525700", "Москва"),
    SPB("044030723", "С-Петербург"),
    NNV("042202847", "Нижний Новгород"),
    NSK("045004799", "Новосибирск"),
    KRD("040349556", "Краснодар"),
    EKB("046577906", "Екатеринбург");

    private String bik;
    private String name;

    private Branch(String bik, String name) {
        this.bik = bik;
        this.name = name;
    }

    public String getBik() {
        return this.bik;
    }

    public String getName() {
        return this.name;
    }
}
