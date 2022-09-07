package br.com.desafio.backvotos.application.voto.dto;

import br.com.desafio.backvotos.application.pauta.dto.IniciarSessaoInput;
import br.com.desafio.backvotos.domain.enums.TipoVotoEnum;

public record CadastrarVotoInput(
        String idPauta,
        String cpf,
        TipoVotoEnum valor
) {
    public static CadastrarVotoInput create(final String idPauta, final String cpf, final TipoVotoEnum valor) {
        return new CadastrarVotoInput(idPauta, cpf, valor);
    }
}
