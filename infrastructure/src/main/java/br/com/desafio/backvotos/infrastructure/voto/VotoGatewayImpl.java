package br.com.desafio.backvotos.infrastructure.voto;

import br.com.desafio.backvotos.domain.voto.Voto;
import br.com.desafio.backvotos.domain.voto.VotoGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotoGatewayImpl implements VotoGateway {

    @Override
    public Voto insert(Voto voto) {
        return null;
    }

    @Override
    public boolean isValidCpf(String cpf) {
        return false;
    }

    @Override
    public Optional<Voto> getById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Voto> findAll(String idPauta) {
        return null;
    }
}
