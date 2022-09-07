package br.com.desafio.backvotos.application.pauta.usecase.cadastrar;

import br.com.desafio.backvotos.application.pauta.dto.CadastrarPautaInput;
import br.com.desafio.backvotos.application.pauta.dto.PautaOutput;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import br.com.desafio.backvotos.domain.validation.handler.ValidationHandler;

public class CadastrarPautaUseCaseImpl implements CadastrarPautaUseCase {

    private final PautaGateway gateway;

    public CadastrarPautaUseCaseImpl(final PautaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public PautaOutput execute(CadastrarPautaInput input) {
        var nome = input.nome();
        var pauta = Pauta.create(nome);
        pauta.validate(new ValidationHandler());
        var retorno = gateway.insert(pauta);
        return PautaOutput.toOutput(retorno);
    }
}
