package br.com.desafio.backvotos.infrastructure.resultado.mapper;

import br.com.desafio.backvotos.application.pauta.dto.PautaOutput;
import br.com.desafio.backvotos.application.resultado.dto.ResultadoOutput;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.resultado.Resultado;
import br.com.desafio.backvotos.infrastructure.pauta.model.PautaResponse;
import br.com.desafio.backvotos.infrastructure.pauta.persistence.PautaJpa;
import br.com.desafio.backvotos.infrastructure.resultado.model.ResultadoResponse;
import br.com.desafio.backvotos.infrastructure.resultado.persistence.ResultadoJpa;

public class ResultadoMapper {

    public static ResultadoJpa toPersistence(final Resultado resultado) {
        return ResultadoJpa.builder()
                .id(resultado.getId())
                .idPauta(resultado.getIdPauta())
                .sim(resultado.getSim())
                .nao(resultado.getNao())
                .createdAt(resultado.getCreatedAt()).build();
    }

    public static Resultado toDomain(final ResultadoJpa resultadoJpa) {
        return Resultado.clonar(
                resultadoJpa.getId(),
                resultadoJpa.getIdPauta(),
                resultadoJpa.getSim(),
                resultadoJpa.getNao(),
                resultadoJpa.getCreatedAt()
        );
    }

    public static ResultadoResponse toResponse(final ResultadoOutput output) {
        return new ResultadoResponse(
                output.id(),
                output.idPauta(),
                output.sim(),
                output.nao(),
                output.createdAt()
        );
    }

}
