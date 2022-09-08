package br.com.desafio.backvotos.application.resultado.usecase.get;

import br.com.desafio.backvotos.application.resultado.dto.ResultadoOutput;
import br.com.desafio.backvotos.domain.exception.NotFoundException;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.resultado.ResultadoGateway;

public class GetResultadoUseCaseImpl implements GetResultadoUseCase {

    private final ResultadoGateway gateway;

    public GetResultadoUseCaseImpl(final ResultadoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public ResultadoOutput execute(String idPauta) {
        return gateway.getByIdPauta(idPauta)
                .map(ResultadoOutput::toOutput)
                .orElseThrow(() -> NotFoundException.with(Pauta.class, idPauta));
    }
}
