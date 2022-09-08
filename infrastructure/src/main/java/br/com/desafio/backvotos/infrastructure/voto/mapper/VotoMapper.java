package br.com.desafio.backvotos.infrastructure.voto.mapper;

import br.com.desafio.backvotos.application.voto.dto.VotoOutput;
import br.com.desafio.backvotos.domain.enums.TipoVotoEnum;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.voto.Voto;
import br.com.desafio.backvotos.infrastructure.pauta.persistence.PautaJpa;
import br.com.desafio.backvotos.infrastructure.voto.model.VotoResponse;
import br.com.desafio.backvotos.infrastructure.voto.persistence.VotoJpa;

public class VotoMapper {

    public static VotoResponse toResponse(final VotoOutput output) {
        return new VotoResponse(
                output.id(),
                output.idPauta(),
                output.cpf(),
                output.valor(),
                output.createdAt()
        );
    }

    public static VotoJpa toPersistence(final Voto voto) {
        return VotoJpa.builder()
                .id(voto.getId())
                .idPauta(voto.getIdPauta())
                .cpf(voto.getCpf())
                .valor(voto.getValor().getValor())
                .createdAt(voto.getCreatedAt()).build();
    }

    public static Voto toDomain(final VotoJpa pautaJpa) {
        return Voto.clonar(
                pautaJpa.getId(),
                pautaJpa.getIdPauta(),
                pautaJpa.getCpf(),
                TipoVotoEnum.valueOf(pautaJpa.getValor()),
                pautaJpa.getCreatedAt()
        );
    }
}
