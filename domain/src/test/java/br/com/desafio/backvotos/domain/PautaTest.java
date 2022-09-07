package br.com.desafio.backvotos.domain;

import br.com.desafio.backvotos.domain.exception.DomainException;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.validation.handler.ValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class PautaTest {

    @Test
    public void givenValidParams_whenCreataAPauta_thenReturnAValidPauta() {
        final var nome = "Pauta 1";

        var pauta = Pauta.create(nome);
        pauta.validate(new ValidationHandler());

        Assertions.assertNotNull(pauta);
        Assertions.assertNotNull(pauta.getId());

        Assertions.assertEquals(nome, pauta.getNome());
        Assertions.assertNotNull(pauta.getCreatedAt());
        Assertions.assertNotNull(pauta.getUpdatedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewPautaAndValidate_thenShouldReceiveError() {
        final String name = null;
        final var messageError = "'nome' não pode ser null";
        final var messageCount = 1;

        var pauta = Pauta.create(name);

        var exception = Assertions.assertThrows(DomainException.class, () -> pauta.validate(new ValidationHandler()));

        Assertions.assertEquals(messageError, exception.getErrors().get(0));
        Assertions.assertEquals(messageCount, exception.getErrors().size());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewPautaAndValidate_thenShouldReceiveError() {
        final String name = " ";
        final var messageErrorEmpty = "'nome' não pode ser empty";
        final var messageErrorLength = "'nome' deve conter entre 3 e 255 caracteres";
        final var messageCount = 2;

        var pauta = Pauta.create(name);

        var exception = Assertions.assertThrows(DomainException.class, () -> pauta.validate(new ValidationHandler()));

        Assertions.assertEquals(messageErrorEmpty, exception.getErrors().get(0));
        Assertions.assertEquals(messageErrorLength, exception.getErrors().get(1));
        Assertions.assertEquals(messageCount, exception.getErrors().size());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewPautaAndValidate_thenShouldReceiveError() {
        final String name = "pa ";
        final var messageError = "'nome' deve conter entre 3 e 255 caracteres";
        final var messageCount = 1;

        var pauta = Pauta.create(name);

        var exception = Assertions.assertThrows(DomainException.class, () -> pauta.validate(new ValidationHandler()));

        Assertions.assertEquals(messageError, exception.getErrors().get(0));
        Assertions.assertEquals(messageCount, exception.getErrors().size());
    }

    @Test
    public void givenAnInvalidNameLengthMoreThan255_whenCallNewPautaAndValidate_thenShouldReceiveError() {
        final String name = """
                Gostaria de enfatizar que o entendimento das metas propostas deve passar por modificações independentemente dos procedimentos 
                normalmente adotados. Percebemos, cada vez mais, que o novo modelo estrutural aqui preconizado garante a contribuição de um grupo 
                importante na determinação das novas proposições. Todas estas questões
                """;
        final var messageError = "'nome' deve conter entre 3 e 255 caracteres";
        final var messageCount = 1;

        var pauta = Pauta.create(name);

        var exception = Assertions.assertThrows(DomainException.class, () -> pauta.validate(new ValidationHandler()));

        Assertions.assertEquals(messageError, exception.getErrors().get(0));
        Assertions.assertEquals(messageCount, exception.getErrors().size());
    }

    @Test
    public void givenAValidPauta_whenCallUpdate_thenReturnPautaUpdated() {
        final var nome = "Pauta 1";
        final var novoNome = "Pauta 2";
        final var novaDataInicio = Instant.now();
        final var novaDataFim = Instant.now();

        var pauta = Pauta.create(nome);
        pauta.validate(new ValidationHandler());

        Assertions.assertNotNull(pauta);
        Assertions.assertNotNull(pauta.getId());

        pauta.update(novoNome, novaDataInicio, novaDataFim);
        pauta.validate(new ValidationHandler());

        Assertions.assertNotNull(pauta);
        Assertions.assertNotNull(pauta.getId());

        Assertions.assertEquals(novoNome, pauta.getNome());
        Assertions.assertEquals(novaDataInicio, pauta.getDataInicio());
        Assertions.assertEquals(novaDataFim, pauta.getDataFim());
        Assertions.assertNotNull(pauta.getCreatedAt());
        Assertions.assertNotNull(pauta.getUpdatedAt());
    }
}
