package br.com.desafio.backvotos.application.resultado.contabilizar;

import br.com.desafio.backvotos.application.UseCaseTest;
import br.com.desafio.backvotos.application.resultado.dto.ResultadoInput;
import br.com.desafio.backvotos.application.resultado.usecase.contabilizar.ContabilizarResultadoUseCaseImpl;
import br.com.desafio.backvotos.application.voto.dto.CadastrarVotoInput;
import br.com.desafio.backvotos.application.voto.usecase.cadastrar.CadastrarVotoUseCaseImpl;
import br.com.desafio.backvotos.domain.enums.TipoVotoEnum;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import br.com.desafio.backvotos.domain.resultado.Resultado;
import br.com.desafio.backvotos.domain.resultado.ResultadoGateway;
import br.com.desafio.backvotos.domain.voto.Voto;
import br.com.desafio.backvotos.domain.voto.VotoGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class ContabilizarResultadoUseCaseTest extends UseCaseTest {

    @InjectMocks
    private ContabilizarResultadoUseCaseImpl useCase;

    @Mock
    private PautaGateway pautaGateway;

    @Mock
    private ResultadoGateway resultadoGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(pautaGateway);
    }

    @Test
    public void givenAValidDomain_whenCallsContabilizarResultado_thenShouldReturnAValidResultado() {
        final var idPauta = "a286d30a-2da0-11ed-a261-0242ac120002";
        final var now = Instant.now();

        final var input = ResultadoInput.create(idPauta);
        var pauta = Pauta.create("Teste Pauta");
        pauta.update(pauta.getNome(), now.plusSeconds(-10), now.plusSeconds( -1));

        var resultado = Resultado.create(idPauta);

        // validar pauta
        when(pautaGateway.getById(any()))
                .thenReturn(Optional.of(pauta));

        // contabilizar resultado
        when(resultadoGateway.getByIdPauta(any()))
                .thenReturn(Optional.empty());

        when(resultadoGateway.insert(any()))
                .thenReturn(resultado);

        final var output = useCase.execute(input);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());
        Assertions.assertEquals(idPauta, output.idPauta());
        Assertions.assertNotNull(output.createdAt());

        Mockito.verify(pautaGateway, times(1)).getById(any());
        Mockito.verify(resultadoGateway, times(1)).getByIdPauta(any());
        Mockito.verify(resultadoGateway, times(1)).insert(any());
    }
}