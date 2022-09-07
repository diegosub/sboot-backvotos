package br.com.desafio.backvotos.application.voto.cadastrar;

import br.com.desafio.backvotos.application.UseCaseTest;
import br.com.desafio.backvotos.application.pauta.dto.CadastrarPautaInput;
import br.com.desafio.backvotos.application.pauta.usecase.cadastrar.CadastrarPautaUseCaseImpl;
import br.com.desafio.backvotos.application.voto.dto.CadastrarVotoInput;
import br.com.desafio.backvotos.application.voto.usecase.cadastrar.CadastrarVotoUseCaseImpl;
import br.com.desafio.backvotos.domain.enums.TipoVotoEnum;
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

public class CadastrarVotoUseCaseTest extends UseCaseTest {

    @InjectMocks
    private CadastrarVotoUseCaseImpl useCase;

    @Mock
    private PautaGateway gateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(gateway);
    }

    @Test
    public void givenAValidDomain_whenCallsCadastrarVoto_thenShouldReturnAValidVoto() {
//        final var idPauta = "a286d30a-2da0-11ed-a261-0242ac120002";
//        final var cpf = "90307514013";
//        final var valor = TipoVotoEnum.SIM;
//
//        var input = CadastrarVotoInput.create(idPauta, cpf, valor);
//
//        when(gateway.insert(any()))
//                .thenAnswer(returnsFirstArg());
//
//        final var output = useCase.execute(input);
//
//        Assertions.assertNotNull(output);
//        Assertions.assertNotNull(output.id());
//        Assertions.assertEquals(idPauta, output.idPauta());
//        Assertions.assertEquals(cpf, output.cpf());
//        Assertions.assertEquals(valor, output.valor());
//        Assertions.assertNotNull(output.createdAt());
//
//        Mockito.verify(gateway, times(1)).insert(any());
    }
}
