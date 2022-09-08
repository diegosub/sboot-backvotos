package br.com.desafio.backvotos.domain.resultado;

import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.search.Pagination;
import br.com.desafio.backvotos.domain.search.SearchQuery;

import java.util.Optional;

public interface ResultadoGateway {

    Resultado insert(Resultado resultado);

    Optional<Resultado> getByIdPauta(String idPauta);

    void sendResult(String idPauta);

}
