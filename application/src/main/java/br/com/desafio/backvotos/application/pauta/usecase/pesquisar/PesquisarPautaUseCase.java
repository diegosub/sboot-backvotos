package br.com.desafio.backvotos.application.pauta.usecase.pesquisar;

import br.com.desafio.backvotos.application.UseCase;
import br.com.desafio.backvotos.application.pauta.dto.PautaOutput;
import br.com.desafio.backvotos.domain.search.Pagination;
import br.com.desafio.backvotos.domain.search.SearchQuery;

public interface PesquisarPautaUseCase extends UseCase<SearchQuery, Pagination<PautaOutput>> {
}
