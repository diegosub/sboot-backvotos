package br.com.desafio.backvotos.domain.voto;

import br.com.desafio.backvotos.domain.enums.TipoVotoEnum;
import br.com.desafio.backvotos.domain.validation.handler.ValidationHandler;

import java.time.Instant;
import java.util.UUID;

public class Voto {

    private String id;
    private String idPauta;
    private String cpf;
    private TipoVotoEnum valor;
    private Instant createdAt;

    private Voto(
            final String id,
            final String idPauta,
            final String cpf,
            final TipoVotoEnum valor,
            final Instant createdAt
    ) {
        this.id = id;
        this.idPauta = idPauta;
        this.cpf = cpf;
        this.valor = valor;
        this.createdAt = createdAt;
    }

    public static Voto create(final String idPauta, final String cpf, final TipoVotoEnum valor) {
        final var id = UUID.randomUUID().toString();
        final var now = Instant.now();
        return new Voto(id, idPauta, cpf, valor, now);
    }

    public void validate(final ValidationHandler handler) {
        new VotoValidation(this, handler).validate();
    }

    public String getId() {
        return id;
    }

    public String getIdPauta() {
        return idPauta;
    }

    public String getCpf() {
        return cpf;
    }

    public TipoVotoEnum getValor() {
        return valor;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
