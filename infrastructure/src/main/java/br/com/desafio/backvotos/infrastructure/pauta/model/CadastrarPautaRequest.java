package br.com.desafio.backvotos.infrastructure.pauta.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CadastrarPautaRequest(
        @JsonProperty("nome") String nome
) {
}
