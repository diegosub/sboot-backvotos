package br.com.desafio.backvotos.infrastructure.pauta.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IniciarSessaoRequest(
        @JsonProperty("minutos") Integer minutos
) {
}
