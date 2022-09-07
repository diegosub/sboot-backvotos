package br.com.desafio.backvotos.application.pauta.usecase.pesquisar;

import br.com.desafio.backvotos.application.pauta.dto.PautaOutput;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import br.com.desafio.backvotos.domain.search.Pagination;
import br.com.desafio.backvotos.domain.search.SearchQuery;

public class PesquisarPautaUseCaseImpl implements PesquisarPautaUseCase {

    private final PautaGateway gateway;

    public PesquisarPautaUseCaseImpl(final PautaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Pagination<PautaOutput> execute(SearchQuery query) {
        return this.gateway.pesquisar(query)
                .map(PautaOutput::toOutput);
    }
}
