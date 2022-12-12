package br.com.banco.enums;

public enum TipoChaveEnum {
    CELULAR("celular"),
    EMAIL("email"),
    CPF("cpf"),
    CNPJ("cnpj"),
    ALEATORIO("aleatorio");

    private String value;

    TipoChaveEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

