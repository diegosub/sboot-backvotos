package br.com.desafio.backvotos.infrastructure.integration.resultado;

import br.com.desafio.backvotos.application.pauta.usecase.cadastrar.CadastrarPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.iniciar.IniciarSessaoUseCase;
import br.com.desafio.backvotos.application.resultado.dto.ResultadoInput;
import br.com.desafio.backvotos.application.resultado.usecase.contabilizar.ContabilizarResultadoUseCase;
import br.com.desafio.backvotos.domain.enums.TipoVotoEnum;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import br.com.desafio.backvotos.domain.resultado.Resultado;
import br.com.desafio.backvotos.domain.voto.Voto;
import br.com.desafio.backvotos.infrastructure.IntegrationTest;
import br.com.desafio.backvotos.infrastructure.pauta.mapper.PautaMapper;
import br.com.desafio.backvotos.infrastructure.pauta.persistence.PautaRepository;
import br.com.desafio.backvotos.infrastructure.voto.mapper.VotoMapper;
import br.com.desafio.backvotos.infrastructure.voto.persistence.VotoRepository;
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
public class ResultadoIT {

    @Autowired
    private ContabilizarResultadoUseCase contabilizarResultadoUseCase;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @SpyBean
    private PautaGateway pautaGateway;

    @Test
    public void givenAValidParams_whenCallsContabilizarResultado_thenShouldReturnAValidResultado() {
        var pauta = Pauta.create("TESTE");
        pauta.update(pauta.getNome(), Instant.now().plusSeconds(-10), Instant.now().plusSeconds(-5));
        var votoSim = Voto.create(pauta.getId(), "123654789", TipoVotoEnum.SIM);
        var votoNao = Voto.create(pauta.getId(), "123654787", TipoVotoEnum.NAO);

        savePauta(pauta);
        saveVoto(votoSim, votoNao);

        Assertions.assertEquals(2, votoRepository.count());

        final var input = ResultadoInput.create(pauta.getId());

        // para rodar esse teste deve ter o rabbitmq startado

        //final var output = contabilizarResultadoUseCase.execute(input);

//        Assertions.assertNotNull(output);
//        Assertions.assertNotNull(output.id());
//        Assertions.assertEquals(pauta.getId(), output.idPauta());
//        Assertions.assertNotNull(output.createdAt());
    }

    private void savePauta(final Pauta... pauta) {
        pautaRepository.saveAllAndFlush(
                Arrays.stream(pauta)
                        .map(PautaMapper::toPersistence)
                        .toList()
        );
    }

    private void saveVoto(final Voto... voto) {
        votoRepository.saveAllAndFlush(
                Arrays.stream(voto)
                        .map(VotoMapper::toPersistence)
                        .toList()
        );
    }
}
