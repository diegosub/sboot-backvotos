package br.com.desafio.backvotos.infrastructure.config.usecase;

import br.com.desafio.backvotos.application.pauta.usecase.cadastrar.CadastrarPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.cadastrar.CadastrarPautaUseCaseImpl;
import br.com.desafio.backvotos.application.pauta.usecase.get.GetPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.get.GetPautaUseCaseImpl;
import br.com.desafio.backvotos.application.pauta.usecase.iniciar.IniciarSessaoUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.iniciar.IniciarSessaoUseCaseImpl;
import br.com.desafio.backvotos.application.pauta.usecase.pesquisar.PesquisarPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.pesquisar.PesquisarPautaUseCaseImpl;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PautaUseCaseConfig {

    private final PautaGateway pautaGateway;

    public PautaUseCaseConfig(final PautaGateway pautaGateway) {
        this.pautaGateway = pautaGateway;
    }

    @Bean
    public CadastrarPautaUseCase cadastrarPautaUseCase() {
        return new CadastrarPautaUseCaseImpl(pautaGateway);
    }

    @Bean
    public IniciarSessaoUseCase iniciarSessaoUseCase() {
        return new IniciarSessaoUseCaseImpl(pautaGateway);
    }

    @Bean
    public GetPautaUseCase getPautaUseCase() {
        return new GetPautaUseCaseImpl(pautaGateway);
    }

    @Bean
    public PesquisarPautaUseCase pesquisarPautaUseCase() {
        return new PesquisarPautaUseCaseImpl(pautaGateway);
    }
}
