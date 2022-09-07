package br.com.desafio.backvotos.infrastructure.voto.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Voto")
@Table(name = "tb_voto")
public class VotoJpa {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "id_pauta", nullable = false)
    private String idPauta;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "valor", nullable = false)
    private String valor;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

}
