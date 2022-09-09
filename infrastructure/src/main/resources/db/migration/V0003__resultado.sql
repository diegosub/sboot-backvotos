CREATE TABLE tb_resultado (
    id VARCHAR(36) NOT NULL,
    id_pauta VARCHAR(36) NOT NULL,
    sim NUMERIC NOT NULL,
    nao NUMERIC NOT NULL,
    created_at timestamp without time zone NOT NULL,

    CONSTRAINT pk_resultado PRIMARY KEY (id),
    CONSTRAINT fk_resultado_pauta FOREIGN KEY (id_pauta)
            REFERENCES tb_pauta (id)
);