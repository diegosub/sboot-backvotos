CREATE TABLE tb_pauta (
    id VARCHAR(36) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    data_inicio timestamp without time zone,
    data_fim timestamp without time zone,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,

    CONSTRAINT pk_pauta PRIMARY KEY (id)
);