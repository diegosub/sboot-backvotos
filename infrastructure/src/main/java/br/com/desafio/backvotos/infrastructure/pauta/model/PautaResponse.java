package br.com.desafio.backvotos.infrastructure.pauta.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record PautaResponse(
        @JsonProperty("id") String id,
        @JsonProperty("nome") String nome,
        @JsonProperty("data_inicio") Instant dataInicio,
        @JsonProperty("data_fim") Instant dataFim,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt
) {
}
