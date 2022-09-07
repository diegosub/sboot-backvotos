package br.com.desafio.backvotos.infrastructure.pauta.mapper;

import br.com.desafio.backvotos.application.pauta.dto.CadastrarPautaInput;
import br.com.desafio.backvotos.application.pauta.dto.IniciarSessaoInput;
import br.com.desafio.backvotos.application.pauta.dto.PautaOutput;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.infrastructure.pauta.model.CadastrarPautaRequest;
import br.com.desafio.backvotos.infrastructure.pauta.model.IniciarSessaoRequest;
import br.com.desafio.backvotos.infrastructure.pauta.model.PautaResponse;
import br.com.desafio.backvotos.infrastructure.pauta.persistence.PautaJpa;

public class PautaMapper {

    public static PautaJpa toPersistence(final Pauta pauta) {
        return PautaJpa.builder()
                .id(pauta.getId())
                .nome(pauta.getNome())
                .dataInicio(pauta.getDataInicio())
                .dataFim(pauta.getDataFim())
                .createdAt(pauta.getCreatedAt())
                .updatedAt(pauta.getUpdatedAt()).build();
    }

    public static Pauta toDomain(final PautaJpa pautaJpa) {
        return Pauta.clonar(
                pautaJpa.getId(),
                pautaJpa.getNome(),
                pautaJpa.getDataInicio(),
                pautaJpa.getDataFim(),
                pautaJpa.getCreatedAt(),
                pautaJpa.getUpdatedAt()
        );
    }

    public static PautaResponse toResponse(final PautaOutput output) {
        return new PautaResponse(
                output.id(),
                output.nome(),
                output.dataInicio(),
                output.dataFim(),
                output.createdAt(),
                output.updatedAt()
        );
    }
}
