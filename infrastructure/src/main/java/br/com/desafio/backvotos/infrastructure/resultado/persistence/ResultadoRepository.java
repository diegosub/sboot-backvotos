package br.com.desafio.backvotos.infrastructure.resultado.persistence;

import br.com.desafio.backvotos.infrastructure.resultado.model.ResultadoVO;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Lazy(true)
public interface ResultadoRepository extends JpaRepository<ResultadoJpa, String> {

    @Query("SELECT v.valor AS propriedade, count(v) AS valor FROM Voto v WHERE v.idPauta = :idPauta GROUP BY v.valor ORDER BY v.valor DESC")
    List<Object> listByIdPauta(@Param("idPauta") String idPauta);

    Optional<ResultadoJpa> findByIdPauta(String idPauta);

}