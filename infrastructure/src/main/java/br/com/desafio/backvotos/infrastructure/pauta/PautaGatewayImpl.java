package br.com.desafio.backvotos.infrastructure.pauta;

import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import br.com.desafio.backvotos.domain.search.Pagination;
import br.com.desafio.backvotos.domain.search.SearchQuery;
import br.com.desafio.backvotos.infrastructure.pauta.mapper.PautaMapper;
import br.com.desafio.backvotos.infrastructure.pauta.persistence.PautaRepository;
import br.com.desafio.backvotos.infrastructure.voto.model.VotoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PautaGatewayImpl implements PautaGateway {

    @Autowired
    private PautaRepository repository;

    private VotoRepos

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
    public Integer countCpfByPauta(String cpf, String idPauta) {
        return repository.countCpfByPauta(cpf, idPauta);
        return null;
    }

    @Override
    public Optional<Pauta> getById(String id) {
        return repository.findById(id).map(PautaMapper::toDomain);
    }

    @Override
    public Pagination<Pauta> pesquisar(SearchQuery query) {
        return null;
    }
}
