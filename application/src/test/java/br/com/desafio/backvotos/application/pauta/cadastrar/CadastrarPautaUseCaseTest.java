package br.com.desafio.backvotos.application.pauta.cadastrar;

import br.com.desafio.backvotos.application.UseCaseTest;
import br.com.desafio.backvotos.application.pauta.dto.CadastrarPautaInput;
import br.com.desafio.backvotos.application.pauta.usecase.cadastrar.CadastrarPautaUseCaseImpl;
import br.com.desafio.backvotos.domain.exception.DomainException;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CadastrarPautaUseCaseTest extends UseCaseTest {

    @InjectMocks
    private CadastrarPautaUseCaseImpl useCase;

    @Mock
    private PautaGateway gateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(gateway);
    }

    @Test
    public void givenAValidDomain_whenCallsCadastrarPauta_thenShouldReturnAValidPauta() {
        final var nomeEsperado = "Pauta 1";

        var input = CadastrarPautaInput.create(nomeEsperado);

        when(gateway.insert(any()))
                .thenAnswer(returnsFirstArg());

        final var output = useCase.execute(input);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());
        Assertions.assertEquals(nomeEsperado, output.nome());
        Assertions.assertNull(output.dataInicio());
        Assertions.assertNull(output.dataFim());
        Assertions.assertNotNull(output.createdAt());
        Assertions.assertNotNull(output.updatedAt());

        Mockito.verify(gateway, times(1)).insert(any());
    }

    @Test
    public void givenAInvalidParams_whenCallsCadastrarPauta_thenShouldReturnDomainException() {
        final String nome = null;

        final var messageNomeError = "'nome' não pode ser null";
        final var messageCount = 1;

        var input = CadastrarPautaInput.create(nome);

        final var exception = Assertions.assertThrows(DomainException.class, () -> useCase.execute(input));

        Assertions.assertEquals(messageCount, exception.getErrors().size());
        Assertions.assertEquals(messageNomeError, exception.getErrors().get(0));

        Mockito.verify(gateway, times(0)).insert(any());
    }
}
