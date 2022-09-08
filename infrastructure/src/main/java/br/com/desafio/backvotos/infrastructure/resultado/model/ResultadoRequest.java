package br.com.desafio.backvotos.infrastructure.resultado.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResultadoRequest(
        @JsonProperty("id_pauta") String idPauta
) {
}
