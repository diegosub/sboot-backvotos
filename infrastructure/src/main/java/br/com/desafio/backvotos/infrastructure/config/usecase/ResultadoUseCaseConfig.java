package br.com.desafio.backvotos.infrastructure.config.usecase;

import br.com.desafio.backvotos.application.resultado.usecase.contabilizar.ContabilizarResultadoUseCase;
import br.com.desafio.backvotos.application.resultado.usecase.contabilizar.ContabilizarResultadoUseCaseImpl;
import br.com.desafio.backvotos.application.resultado.usecase.get.GetResultadoUseCase;
import br.com.desafio.backvotos.application.resultado.usecase.get.GetResultadoUseCaseImpl;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import br.com.desafio.backvotos.domain.resultado.ResultadoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResultadoUseCaseConfig {

    private final ResultadoGateway resultadoGateway;

    private final PautaGateway pautaGateway;

    public ResultadoUseCaseConfig(
            final ResultadoGateway resultadoGateway,
            final PautaGateway pautaGateway
    ) {
        this.resultadoGateway = resultadoGateway;
        this.pautaGateway = pautaGateway;
    }

    @Bean
    public ContabilizarResultadoUseCase contabilizarResultadoUseCase() {
        return new ContabilizarResultadoUseCaseImpl(resultadoGateway, pautaGateway);
    }

    @Bean
    public GetResultadoUseCase getResultadoUseCase() {
        return new GetResultadoUseCaseImpl(resultadoGateway);
    }

}
