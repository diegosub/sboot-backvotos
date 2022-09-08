package br.com.desafio.backvotos.infrastructure.pauta.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResultadoResponse(
        @JsonProperty("sim") Integer sim,
        @JsonProperty("nao") Integer nao
) {
}
