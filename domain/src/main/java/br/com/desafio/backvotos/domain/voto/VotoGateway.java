package br.com.desafio.backvotos.domain.voto;

import java.util.List;
import java.util.Optional;

public interface VotoGateway {

    Voto insert(Voto voto);

    boolean isValidCpf(String cpf);

    Optional<Voto> getById(String id);

    List<Voto> findAll(String idPauta);

}
