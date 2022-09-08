package br.com.desafio.backvotos.infrastructure.resultado.persistence;

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
@Entity(name = "Resultado")
@Table(name = "tb_resultado")
public class ResultadoJpa {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "id_pauta", nullable = false)
    private String idPauta;

    @Column(name = "sim", nullable = false)
    private Long sim;

    @Column(name = "nao", nullable = false)
    private Long nao;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

}
