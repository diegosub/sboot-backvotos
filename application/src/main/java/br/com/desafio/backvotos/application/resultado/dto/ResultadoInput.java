package br.com.desafio.backvotos.application.resultado.dto;

import br.com.desafio.backvotos.application.pauta.dto.CadastrarPautaInput;

public record ResultadoInput(
        String idPauta
) {
    public static ResultadoInput create(final String idPauta) {
        return new ResultadoInput(idPauta);
    }
}
