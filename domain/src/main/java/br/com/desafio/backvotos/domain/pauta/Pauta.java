package br.com.desafio.backvotos.domain.pauta;

import br.com.desafio.backvotos.domain.validation.handler.ValidationHandler;

import java.time.Instant;
import java.util.UUID;

public class Pauta {

    private String id;
    private String nome;
    private Instant dataInicio;
    private Instant dataFim;
    private Instant createdAt;
    private Instant updatedAt;


    private Pauta(
            final String id,
            final String nome,
            final Instant dataInicio,
            final Instant dataFim,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Pauta create(final String id, final String nome) {
        final var now = Instant.now();
        return new Pauta(id, nome, null, null, now, now);
    }

    public static Pauta create(final String nome) {
        final var id = UUID.randomUUID().toString();
        final var now = Instant.now();
        return new Pauta(id, nome, null, null, now, now);
    }

    public static Pauta clonar(
        final String id,
        final String nome,
        final Instant dataInicio,
        final Instant dataFim,
        final Instant createdAt,
        final Instant updatedAt
    ) {
        return new Pauta(id, nome, dataInicio, dataFim, createdAt, updatedAt);
    }

    public void update(
            final String nome,
            final Instant dataInicio,
            final Instant dataFim) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.updatedAt = Instant.now();
    }

    public void validate(final ValidationHandler handler) {
        new PautaValidation(this, handler).validate();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Instant getDataInicio() {
        return dataInicio;
    }

    public Instant getDataFim() {
        return dataFim;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
