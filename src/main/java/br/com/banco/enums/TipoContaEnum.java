package br.com.banco.enums;

public enum TipoContaEnum {
    CORRENTE("corrente"), POUPANCA("poupança");

    private String value;

    TipoContaEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
