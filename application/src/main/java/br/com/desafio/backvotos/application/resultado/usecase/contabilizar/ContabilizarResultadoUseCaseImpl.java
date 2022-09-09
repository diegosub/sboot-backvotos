package br.com.desafio.backvotos.application.resultado.usecase.contabilizar;

import br.com.desafio.backvotos.application.resultado.dto.ResultadoInput;
import br.com.desafio.backvotos.application.resultado.dto.ResultadoOutput;
import br.com.desafio.backvotos.domain.exception.DomainException;
import br.com.desafio.backvotos.domain.exception.NotFoundException;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import br.com.desafio.backvotos.domain.resultado.Resultado;
import br.com.desafio.backvotos.domain.resultado.ResultadoGateway;
import br.com.desafio.backvotos.domain.validation.handler.ValidationHandler;

import javax.xml.transform.Result;
import java.time.Instant;

public class ContabilizarResultadoUseCaseImpl implements ContabilizarResultadoUseCase {

    private final ResultadoGateway resultadoGateway;

    private final PautaGateway pautaGateway;

    public ContabilizarResultadoUseCaseImpl(
            final ResultadoGateway resultadoGateway,
            final PautaGateway pautaGateway
    ) {
        this.resultadoGateway = resultadoGateway;
        this.pautaGateway = pautaGateway;
    }

    @Override
    public ResultadoOutput execute(ResultadoInput input) {
        var idPauta = input.idPauta();

        this.validarPauta(idPauta);
        this.validarResultado(idPauta);

        var resultado = Resultado.create(idPauta);
        resultado.validate(new ValidationHandler());
        var retorno = resultadoGateway.insert(resultado);
        resultadoGateway.sendResult(idPauta);
        return ResultadoOutput.toOutput(retorno);
    }

    private void validarPauta(String idPauta) {
        var pauta = pautaGateway.getById(idPauta)
                .orElseThrow(() -> NotFoundException.with(Pauta.class, idPauta));

        if(pauta.getDataFim() == null
                || pauta.getDataFim().isAfter(Instant.now())) {
            throw DomainException.with("Esta pauta ainda está com uma sessão de votação aberta");
        }
    }

    private void validarResultado(String idPauta) {
        var resultado = resultadoGateway.getByIdPauta(idPauta);

        if(!resultado.isEmpty()) {
            throw DomainException.with("O Resultado desta pauta já foi contabilizado.");
        }
    }
}
