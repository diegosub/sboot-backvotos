package br.com.desafio.backvotos.domain.enums;

public enum TipoVotoEnum {
    SIM("SIM"),
    NAO("NAO");

    private String valor;

    TipoVotoEnum(final String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
