package br.com.desafio.backvotos.application.pauta.dto;

public record CadastrarPautaInput(
        String nome
) {
    public static CadastrarPautaInput create(final String nome) {
        return new CadastrarPautaInput(nome);
    }
}
