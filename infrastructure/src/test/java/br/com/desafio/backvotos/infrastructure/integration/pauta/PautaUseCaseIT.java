package br.com.desafio.backvotos.infrastructure.integration.pauta;

import br.com.desafio.backvotos.application.pauta.dto.CadastrarPautaInput;
import br.com.desafio.backvotos.application.pauta.dto.IniciarSessaoInput;
import br.com.desafio.backvotos.application.pauta.usecase.cadastrar.CadastrarPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.iniciar.IniciarSessaoUseCase;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import br.com.desafio.backvotos.infrastructure.IntegrationTest;
import br.com.desafio.backvotos.infrastructure.pauta.mapper.PautaMapper;
import br.com.desafio.backvotos.infrastructure.pauta.persistence.PautaJpa;
import br.com.desafio.backvotos.infrastructure.pauta.persistence.PautaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@IntegrationTest
public class PautaUseCaseIT {

    @Autowired
    private CadastrarPautaUseCase cadastrarPautaUseCase;

    @Autowired
    private IniciarSessaoUseCase iniciarSessaoUseCase;

    @Autowired
    private PautaRepository pautaRepository;

    @SpyBean
    private PautaGateway pautaGateway;

    @Test
    public void givenAValidParams_whenCallsCadastrarPauta_thenShouldReturnAValidPauta() {
        final var nomeEsperado = "Pauta 1";

        Assertions.assertEquals(0, pautaRepository.count());

        final var input = CadastrarPautaInput.create(nomeEsperado);

        final var output = cadastrarPautaUseCase.execute(input);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        Assertions.assertEquals(1, pautaRepository.count());

        final var pauta =
                pautaRepository.findById(output.id()).get();

        Assertions.assertEquals(nomeEsperado, pauta.getNome());
        Assertions.assertNotNull(pauta.getCreatedAt());
        Assertions.assertNotNull(pauta.getUpdatedAt());
    }

    @Test
    public void givenAValidParams_whenCallsIniciarSessao_thenShouldReturnAValidPauta() {
        final var minutos = 10;
        final var pauta = Pauta.create("Pauta teste");

        save(pauta);

        var input = IniciarSessaoInput.create(pauta.getId(), minutos);

        Assertions.assertEquals(1, pautaRepository.count());

        final var output = iniciarSessaoUseCase.execute(input);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());
        Assertions.assertEquals(pauta.getId(), output.id());
        Assertions.assertNotNull(output.dataInicio());
        Assertions.assertNotNull(output.dataFim());
        Assertions.assertNotNull(output.createdAt());
        Assertions.assertNotNull(output.updatedAt());
    }

    private void save(final Pauta... pauta) {
        pautaRepository.saveAllAndFlush(
                Arrays.stream(pauta)
                        .map(PautaMapper::toPersistence)
                        .toList()
        );
    }
}
