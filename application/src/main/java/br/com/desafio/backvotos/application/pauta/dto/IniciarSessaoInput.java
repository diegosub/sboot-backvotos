package br.com.desafio.backvotos.application.pauta.dto;

public record IniciarSessaoInput(
        String id,
        Integer minutos
) {
    public static IniciarSessaoInput create(final String id, final Integer minutos) {
        return new IniciarSessaoInput(id, minutos);
    }
}
