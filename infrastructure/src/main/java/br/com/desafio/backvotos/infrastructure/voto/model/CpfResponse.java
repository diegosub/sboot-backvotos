package br.com.desafio.backvotos.infrastructure.voto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CpfResponse(
        @JsonProperty("status") String status
) {
}
