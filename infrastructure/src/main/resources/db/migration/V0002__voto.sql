CREATE TABLE tb_voto (
    id VARCHAR(36) NOT NULL,
    id_pauta VARCHAR(36) NOT NULL,
    cpf VARCHAR(20) UNIQUE NOT NULL,
    valor VARCHAR(3) NOT NULL,
    created_at timestamp without time zone NOT NULL,

    CONSTRAINT pk_voto PRIMARY KEY (id),
    CONSTRAINT uk_cpf_voto UNIQUE (cpf)
);