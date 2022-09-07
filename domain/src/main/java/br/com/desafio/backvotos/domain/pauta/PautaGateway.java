package br.com.desafio.backvotos.domain.pauta;

import br.com.desafio.backvotos.domain.search.Pagination;
import br.com.desafio.backvotos.domain.search.SearchQuery;

import java.util.Optional;

public interface PautaGateway {

    Pauta insert(Pauta pauta);

    Pauta update(Pauta pauta);

    Optional<Pauta> getById(String id);

    Pagination<Pauta> pesquisar(SearchQuery query);

}
