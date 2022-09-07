package br.com.desafio.backvotos.application.pauta.usecase.get;

import br.com.desafio.backvotos.application.pauta.dto.PautaOutput;
import br.com.desafio.backvotos.domain.exception.NotFoundException;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;

public class GetPautaUseCaseImpl implements GetPautaUseCase {

    private final PautaGateway gateway;

    public GetPautaUseCaseImpl(final PautaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public PautaOutput execute(String id) {
        return gateway.getById(id)
                .map(PautaOutput::toOutput)
                .orElseThrow(() -> NotFoundException.with(Pauta.class, id));
    }
}
