package br.com.desafio.backvotos.application.voto.usecase.listar;

import br.com.desafio.backvotos.application.voto.dto.VotoOutput;
import br.com.desafio.backvotos.domain.voto.VotoGateway;

import java.util.List;

public class ListarVotoUseCaseImpl implements ListarVotoUseCase {

    private final VotoGateway gateway;

    public ListarVotoUseCaseImpl(final VotoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<VotoOutput> execute(String idPauta) {
        var lista = gateway.findAll(idPauta);
        return lista.stream().map(VotoOutput::toOutput).toList();
    }
}
