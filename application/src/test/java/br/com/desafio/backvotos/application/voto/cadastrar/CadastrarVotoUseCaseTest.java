package br.com.desafio.backvotos.application.voto.cadastrar;

import br.com.desafio.backvotos.application.UseCaseTest;
import br.com.desafio.backvotos.application.pauta.dto.CadastrarPautaInput;
import br.com.desafio.backvotos.application.pauta.usecase.cadastrar.CadastrarPautaUseCaseImpl;
import br.com.desafio.backvotos.application.voto.dto.CadastrarVotoInput;
import br.com.desafio.backvotos.application.voto.usecase.cadastrar.CadastrarVotoUseCaseImpl;
import br.com.desafio.backvotos.domain.enums.TipoVotoEnum;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
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

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CadastrarVotoUseCaseTest extends UseCaseTest {

    @InjectMocks
    private CadastrarVotoUseCaseImpl useCase;

    @Mock
    private PautaGateway pautaGateway;

    @Mock
    private VotoGateway votoGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(pautaGateway);
    }

    @Test
    public void givenAValidDomain_whenCallsCadastrarVoto_thenShouldReturnAValidVoto() {
        final var idPauta = "a286d30a-2da0-11ed-a261-0242ac120002";
        final var cpf = "90307514013";
        final var valor = TipoVotoEnum.SIM;
        final var now = Instant.now();

        var input = CadastrarVotoInput.create(idPauta, cpf, valor);
        var voto = Voto.create(idPauta, cpf, valor);
        var pauta = Pauta.create("Teste Pauta");
        pauta.update(pauta.getNome(), now.plusSeconds(-1), now.plusSeconds( 10));


        // mockando uma pauta com sessao aberta
        when(pautaGateway.getById(any()))
                .thenReturn(Optional.of(pauta));

        // mockando retorno externo true
        when(votoGateway.isValidCpf(any()))
                .thenReturn(Boolean.TRUE);

        // mockando voto valido (return count 0)
        when(votoGateway.getCountCpfByIdPauta(any(), any()))
                .thenReturn(0);

        when(votoGateway.insert(any()))
                .thenReturn(voto);

        final var output = useCase.execute(input);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());
        Assertions.assertEquals(idPauta, output.idPauta());
        Assertions.assertEquals(cpf, output.cpf());
        Assertions.assertEquals(valor, output.valor());
        Assertions.assertNotNull(output.createdAt());

        Mockito.verify(votoGateway, times(1)).insert(any());
    }
}
