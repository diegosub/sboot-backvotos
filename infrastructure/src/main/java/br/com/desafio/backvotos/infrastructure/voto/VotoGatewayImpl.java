package br.com.desafio.backvotos.infrastructure.voto;

import br.com.desafio.backvotos.domain.voto.Voto;
import br.com.desafio.backvotos.domain.voto.VotoGateway;
import br.com.desafio.backvotos.infrastructure.communication.sync.VerificarCpfSync;
import br.com.desafio.backvotos.infrastructure.pauta.mapper.PautaMapper;
import br.com.desafio.backvotos.infrastructure.voto.mapper.VotoMapper;
import br.com.desafio.backvotos.infrastructure.voto.persistence.VotoJpa;
import br.com.desafio.backvotos.infrastructure.voto.persistence.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotoGatewayImpl implements VotoGateway {

    @Autowired
    private VotoRepository repository;

    @Override
    public Voto insert(Voto voto) {
        var persistence = repository.save(VotoMapper.toPersistence(voto));
        return VotoMapper.toDomain(persistence);
    }

    @Override
    public boolean isValidCpf(String cpf) {
        return VerificarCpfSync.verificarCpfValido(cpf);
    }

    @Override
    public Optional<Voto> getById(String id) {
        return repository.findById(id)
                .map(VotoMapper::toDomain);
    }

    @Override
    public Integer getCountCpfByIdPauta(String cpf, String idPauta) {
        return repository.countCpfByIdPauta(cpf, idPauta);
    }
}
