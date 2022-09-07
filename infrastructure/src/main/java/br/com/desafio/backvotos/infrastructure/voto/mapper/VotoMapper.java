package br.com.desafio.backvotos.infrastructure.voto.mapper;

import br.com.desafio.backvotos.application.voto.dto.VotoOutput;
import br.com.desafio.backvotos.infrastructure.voto.model.VotoResponse;

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

}
