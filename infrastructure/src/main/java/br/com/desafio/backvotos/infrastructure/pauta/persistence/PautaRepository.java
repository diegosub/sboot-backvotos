package br.com.desafio.backvotos.infrastructure.pauta.persistence;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Lazy(true)
public interface PautaRepository extends JpaRepository<PautaJpa, String> {
    Page<PautaJpa> findAll(Specification<PautaJpa> whereClause, Pageable page);
}
