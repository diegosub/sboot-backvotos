package br.com.desafio.backvotos.domain.resultado;

import br.com.desafio.backvotos.domain.pauta.PautaValidation;
import br.com.desafio.backvotos.domain.validation.handler.ValidationHandler;

import java.time.Instant;
import java.util.UUID;

public class Resultado {

    private String id;
    private String idPauta;
    private Long sim;
    private Long nao;
    private Instant createdAt;


    private Resultado(
            final String id,
            final String idPauta,
            final Long sim,
            final Long nao,
            final Instant createdAt
    ) {
        this.id = id;
        this.idPauta = idPauta;
        this.sim = sim;
        this.nao = nao;
        this.createdAt = createdAt;
    }

    public static Resultado create(final String idPauta) {
        final var id = UUID.randomUUID().toString();
        final var now = Instant.now();
        return new Resultado(id, idPauta, null, null, now);
    }

    public void update(
            final Long sim,
            final Long nao) {
        this.sim = sim;
        this.nao = nao;
    }

    public static Resultado clonar(
        final String id,
        final String idPauta,
        final Long sim,
        final Long nao,
        final Instant createdAt
    ) {
        return new Resultado(id, idPauta, sim, nao, createdAt);
    }

    public void validate(final ValidationHandler handler) {
        new ResultadoValidation(this, handler).validate();
    }

    public String getId() {
        return id;
    }

    public String getIdPauta() {
        return idPauta;
    }

    public Long getSim() {
        return sim;
    }

    public Long getNao() {
        return nao;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
