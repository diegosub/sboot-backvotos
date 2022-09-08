package br.com.desafio.backvotos.infrastructure.pauta;

import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import br.com.desafio.backvotos.domain.search.Pagination;
import br.com.desafio.backvotos.domain.search.SearchQuery;
import br.com.desafio.backvotos.infrastructure.pauta.mapper.PautaMapper;
import br.com.desafio.backvotos.infrastructure.pauta.persistence.PautaJpa;
import br.com.desafio.backvotos.infrastructure.pauta.persistence.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.desafio.backvotos.infrastructure.utils.SpecificationUtils.like;

@Service
public class PautaGatewayImpl implements PautaGateway {

    @Autowired
    private PautaRepository repository;
    
    @Override
    public Pauta insert(Pauta pauta) {
        var persistence = repository.save(PautaMapper.toPersistence(pauta));
        return PautaMapper.toDomain(persistence);
    }

    @Override
    public Pauta update(Pauta pauta) {
        var persistence = repository.save(PautaMapper.toPersistence(pauta));
        return PautaMapper.toDomain(persistence);
    }

    @Override
    public Optional<Pauta> getById(String id) {
        return repository.findById(id).map(PautaMapper::toDomain);
    }

    @Override
    public Pagination<Pauta> pesquisar(SearchQuery query) {
        final var page = PageRequest.of(
                query.page(),
                query.perPage(),
                Sort.by(Sort.Direction.fromString(query.direction()), query.sort())
        );

        // Busca dinamica pelo criterio terms (name)
        final var specifications = Optional.ofNullable(query.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult =
                this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(PautaMapper::toDomain).toList()
        );
    }

    private Specification<PautaJpa> assembleSpecification(final String str) {
        final Specification<PautaJpa> nameLike = like("name", str);
        return nameLike;
    }
}
