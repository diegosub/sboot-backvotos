package br.com.desafio.backvotos.infrastructure.voto.persistence;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Lazy(true)
public interface VotoRepository extends JpaRepository<VotoJpa, String> {

    Page<VotoJpa> findAll(Specification<VotoJpa> whereClause, Pageable page);

    @Query("SELECT COUNT(v) FROM Voto v where v.cpf = :cpf AND v.idPauta = :idPauta")
    Integer countCpfByIdPauta(@Param("cpf") String cpf, @Param("idPauta") String idPauta);
}
