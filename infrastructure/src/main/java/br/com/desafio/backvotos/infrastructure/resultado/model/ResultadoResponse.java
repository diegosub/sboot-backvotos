package br.com.desafio.backvotos.infrastructure.resultado.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record ResultadoResponse(
        @JsonProperty("id") String id,
        @JsonProperty("idPauta") String idPauta,
        @JsonProperty("sim") Long sim,
        @JsonProperty("nao") Long nao,
        @JsonProperty("created_at")Instant createdAt
        ) {
}
