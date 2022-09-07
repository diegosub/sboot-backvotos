package br.com.desafio.backvotos.infrastructure.config.usecase;

import br.com.desafio.backvotos.application.pauta.usecase.cadastrar.CadastrarPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.cadastrar.CadastrarPautaUseCaseImpl;
import br.com.desafio.backvotos.application.pauta.usecase.get.GetPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.get.GetPautaUseCaseImpl;
import br.com.desafio.backvotos.application.pauta.usecase.iniciar.IniciarSessaoUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.iniciar.IniciarSessaoUseCaseImpl;
import br.com.desafio.backvotos.application.pauta.usecase.pesquisar.PesquisarPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.pesquisar.PesquisarPautaUseCaseImpl;
import br.com.desafio.backvotos.application.voto.usecase.cadastrar.CadastrarVotoUseCase;
import br.com.desafio.backvotos.application.voto.usecase.cadastrar.CadastrarVotoUseCaseImpl;
import br.com.desafio.backvotos.application.voto.usecase.get.GetVotoUseCase;
import br.com.desafio.backvotos.application.voto.usecase.get.GetVotoUseCaseImpl;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import br.com.desafio.backvotos.domain.voto.VotoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VotoUseCaseConfig {

    private final VotoGateway votoGateway;
    private final PautaGateway pautaGateway;

    public VotoUseCaseConfig(
            final VotoGateway votoGateway,
            final PautaGateway pautaGateway
    ) {
        this.votoGateway = votoGateway;
        this.pautaGateway = pautaGateway;
    }

    @Bean
    public CadastrarVotoUseCase cadastrarVotoUseCase() {
        return new CadastrarVotoUseCaseImpl(votoGateway, pautaGateway);
    }

    @Bean
    public GetVotoUseCase getVotoUseCase() {
        return new GetVotoUseCaseImpl(votoGateway);
    }

}
