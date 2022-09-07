package br.com.desafio.backvotos.infrastructure.voto.model;

import br.com.desafio.backvotos.domain.enums.TipoVotoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CadastrarVotoRequest(
        @JsonProperty("id_pauta") String idPauta,
        @JsonProperty("cpf") String cpf,
        @JsonProperty("valor") TipoVotoEnum valor
) {
}
