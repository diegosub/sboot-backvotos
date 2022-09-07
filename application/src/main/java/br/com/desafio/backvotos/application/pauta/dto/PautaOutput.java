package br.com.desafio.backvotos.application.pauta.dto;

import br.com.desafio.backvotos.domain.pauta.Pauta;

import java.time.Instant;

public record PautaOutput(
        String id,
        String nome,
        Instant dataInicio,
        Instant dataFim,
        Instant createdAt,
        Instant updatedAt
) {
    public static PautaOutput toOutput(Pauta pauta) {
        return new PautaOutput(
                pauta.getId(),
                pauta.getNome(),
                pauta.getDataInicio(),
                pauta.getDataFim(),
                pauta.getCreatedAt(),
                pauta.getUpdatedAt()
        );
    }
}
