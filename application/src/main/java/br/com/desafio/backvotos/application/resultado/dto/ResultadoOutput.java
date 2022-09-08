package br.com.desafio.backvotos.application.resultado.dto;

import br.com.desafio.backvotos.domain.resultado.Resultado;

import java.time.Instant;

public record ResultadoOutput(
        String id,
        String idPauta,
        Long sim,
        Long nao,
        Instant createdAt
) {
    public static ResultadoOutput toOutput(Resultado resultado) {
        return new ResultadoOutput(
                resultado.getId(),
                resultado.getIdPauta(),
                resultado.getSim(),
                resultado.getNao(),
                resultado.getCreatedAt()
        );
    }
}
