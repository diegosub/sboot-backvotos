package br.com.desafio.backvotos.domain;

import br.com.desafio.backvotos.domain.enums.TipoVotoEnum;
import br.com.desafio.backvotos.domain.exception.DomainException;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.validation.handler.ValidationHandler;
import br.com.desafio.backvotos.domain.voto.Voto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VotoTest {

    @Test
    public void givenValidParams_whenCreateAVoto_thenReturnAValidVoto() {
        final var idPauta = "ec01a7d6-2d90-11ed-a261-0242ac120002";
        final var cpf = "71237673062";
        final var valor = TipoVotoEnum.NAO;

        var voto = Voto.create(idPauta, cpf, valor);
        voto.validate(new ValidationHandler());

        Assertions.assertNotNull(voto);
        Assertions.assertNotNull(voto.getId());

        Assertions.assertEquals(idPauta, voto.getIdPauta());
        Assertions.assertEquals(cpf, voto.getCpf());
        Assertions.assertEquals(valor, voto.getValor());
        Assertions.assertNotNull(voto.getCreatedAt());
    }

    @Test
    public void givenAllNullParams_whenCreateAVotoAndValidate_thenShouldReceiveError() {
        final String idPauta = null;
        final String cpf = null;
        final TipoVotoEnum valor = null;

        final var messagePautaError = "'idPauta' não pode ser null";
        final var messageCpfError = "'cpf' não pode ser null";
        final var messageValorError = "'valor' não pode ser null";
        final var messageCount = 3;

        var voto = Voto.create(idPauta, cpf, valor);

        var exception = Assertions.assertThrows(DomainException.class, () -> voto.validate(new ValidationHandler()));

        Assertions.assertEquals(messagePautaError, exception.getErrors().get(0));
        Assertions.assertEquals(messageCpfError, exception.getErrors().get(1));
        Assertions.assertEquals(messageValorError, exception.getErrors().get(2));
        Assertions.assertEquals(messageCount, exception.getErrors().size());
    }

    @Test
    public void givenAllEmptyParams_whenCreateAVotoAndValidate_thenShouldReceiveError() {
        final String idPauta = "";
        final String cpf = "";
        final TipoVotoEnum valor = TipoVotoEnum.SIM;

        final var messagePautaError = "'idPauta' não pode ser empty";
        final var messageCpfError = "'cpf' não pode ser empty";
        final var messageCount = 2;

        var voto = Voto.create(idPauta, cpf, valor);

        var exception = Assertions.assertThrows(DomainException.class, () -> voto.validate(new ValidationHandler()));

        Assertions.assertEquals(messagePautaError, exception.getErrors().get(0));
        Assertions.assertEquals(messageCpfError, exception.getErrors().get(1));
        Assertions.assertEquals(messageCount, exception.getErrors().size());
    }


}
