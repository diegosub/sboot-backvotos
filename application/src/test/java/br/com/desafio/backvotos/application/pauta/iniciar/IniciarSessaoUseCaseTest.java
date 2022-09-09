package br.com.desafio.backvotos.application.pauta.iniciar;

import br.com.desafio.backvotos.application.UseCaseTest;
import br.com.desafio.backvotos.application.pauta.dto.CadastrarPautaInput;
import br.com.desafio.backvotos.application.pauta.dto.IniciarSessaoInput;
import br.com.desafio.backvotos.application.pauta.usecase.iniciar.IniciarSessaoUseCaseImpl;
import br.com.desafio.backvotos.domain.exception.DomainException;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class IniciarSessaoUseCaseTest extends UseCaseTest {

    @InjectMocks
    private IniciarSessaoUseCaseImpl useCase;

    @Mock
    private PautaGateway gateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(gateway);
    }

    @Test
    public void givenAValidDomain_whenCallsIniciarSessao_thenShouldReturnAValidPauta() {
        final var idPauta = "a286d30a-2da0-11ed-a261-0242ac120002";
        final var minutos = 10;
        final var now = Instant.now();
        final var dataInicioEsperado = now;
        final var dataFimEsperado = now.plusSeconds(minutos * 60);

        var input = IniciarSessaoInput.create(idPauta, minutos);

        var pauta = Pauta.create(idPauta, "P 1");
        when(gateway.getById(any())).thenReturn(Optional.of(pauta));
        when(gateway.update(any())).thenReturn(pauta);

        final var output = useCase.execute(input);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());
        Assertions.assertEquals(idPauta, output.id());
        Assertions.assertNotNull(output.dataInicio());
        Assertions.assertNotNull(output.dataFim());
        Assertions.assertNotNull(output.createdAt());
        Assertions.assertNotNull(output.updatedAt());

        Mockito.verify(gateway, times(1)).getById(any());
        Mockito.verify(gateway, times(1)).update(any());
    }

    @Test
    public void givenMinutosNull_whenCallsIniciarSessao_thenShouldReturnAValidPauta() {
        final var idPauta = "a286d30a-2da0-11ed-a261-0242ac120002";
        final Integer minutos = null;
        final var now = Instant.now();
        final var dataInicioEsperado = now;
        final var dataFimEsperado = now.plusSeconds(60);

        var input = IniciarSessaoInput.create(idPauta, minutos);

        var pauta = Pauta.create(idPauta, "P 1");
        when(gateway.getById(any())).thenReturn(Optional.of(pauta));

        pauta.update("P 1", dataInicioEsperado, dataFimEsperado);
        when(gateway.update(any())).thenReturn(pauta);

        final var output = useCase.execute(input);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());
        Assertions.assertEquals(idPauta, output.id());
        Assertions.assertNotNull(output.dataInicio());
        Assertions.assertNotNull(output.dataFim());
        Assertions.assertNotNull(output.createdAt());
        Assertions.assertNotNull(output.updatedAt());

        Mockito.verify(gateway, times(1)).getById(any());
        Mockito.verify(gateway, times(1)).update(any());
    }
}
