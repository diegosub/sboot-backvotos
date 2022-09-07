package br.com.desafio.backvotos.infrastructure.voto.model;

import br.com.desafio.backvotos.domain.enums.TipoVotoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record VotoResponse(
        @JsonProperty("id") String id,
        @JsonProperty("id_pauta") String idPauta,
        @JsonProperty("cpf") String cpf,
        @JsonProperty("valor") TipoVotoEnum valor,
        @JsonProperty("created_at") Instant createdAt
) {
}
