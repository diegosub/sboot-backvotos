package br.com.desafio.backvotos.application.voto.usecase.get;

import br.com.desafio.backvotos.application.voto.dto.VotoOutput;
import br.com.desafio.backvotos.domain.exception.NotFoundException;
import br.com.desafio.backvotos.domain.voto.Voto;
import br.com.desafio.backvotos.domain.voto.VotoGateway;

public class GetVotoUseCaseImpl implements GetVotoUseCase {

    private final VotoGateway gateway;

    public GetVotoUseCaseImpl(final VotoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public VotoOutput execute(String id) {
        return gateway.getById(id)
                .map(VotoOutput::toOutput)
                .orElseThrow(() -> NotFoundException.with(Voto.class, id));
    }
}
