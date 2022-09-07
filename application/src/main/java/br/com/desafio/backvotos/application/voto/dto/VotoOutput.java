package br.com.desafio.backvotos.application.voto.dto;

import br.com.desafio.backvotos.application.pauta.dto.PautaOutput;
import br.com.desafio.backvotos.domain.enums.TipoVotoEnum;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.voto.Voto;

import java.time.Instant;

public record VotoOutput(
        String id,
        String idPauta,
        String cpf,
        TipoVotoEnum valor,
        Instant createdAt
) {
    public static VotoOutput toOutput(Voto voto) {
        return new VotoOutput(
                voto.getId(),
                voto.getIdPauta(),
                voto.getCpf(),
                voto.getValor(),
                voto.getCreatedAt()
        );
    }
}
