package br.com.desafio.backvotos.application.pauta.usecase.iniciar;

import br.com.desafio.backvotos.application.pauta.dto.IniciarSessaoInput;
import br.com.desafio.backvotos.application.pauta.dto.PautaOutput;
import br.com.desafio.backvotos.domain.exception.DomainException;
import br.com.desafio.backvotos.domain.exception.NotFoundException;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import br.com.desafio.backvotos.domain.validation.handler.ValidationHandler;

import java.time.Instant;

public class IniciarSessaoUseCaseImpl implements IniciarSessaoUseCase {

    private final PautaGateway gateway;

    public IniciarSessaoUseCaseImpl(final PautaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public PautaOutput execute(IniciarSessaoInput input) {
        var pauta = gateway.getById(input.id())
                .orElseThrow(() -> NotFoundException.with(Pauta.class, input.id()));

        if(pauta.getDataInicio() != null
                && !pauta.getDataInicio().toString().trim().equals("")) {
            throw DomainException.with("A sessão de votação já foi iniciada para essa pauta");
        }

        var nome = pauta.getNome();
        var dataInicio = Instant.now();
        var dataFim = input.minutos() == null ? dataInicio.plusSeconds(60) : dataInicio.plusSeconds(input.minutos() * 60);

        pauta.update(
                nome,
                dataInicio,
                dataFim
        );
        pauta.validate(new ValidationHandler());
        var retorno = gateway.update(pauta);
        return PautaOutput.toOutput(retorno);
    }
}
