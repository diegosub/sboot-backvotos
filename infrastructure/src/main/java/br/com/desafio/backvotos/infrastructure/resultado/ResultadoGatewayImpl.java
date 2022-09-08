package br.com.desafio.backvotos.infrastructure.resultado;

import br.com.desafio.backvotos.application.resultado.dto.ResultadoInput;
import br.com.desafio.backvotos.application.resultado.dto.ResultadoOutput;
import br.com.desafio.backvotos.domain.exception.NotFoundException;
import br.com.desafio.backvotos.domain.resultado.Resultado;
import br.com.desafio.backvotos.domain.resultado.ResultadoGateway;
import br.com.desafio.backvotos.infrastructure.communication.messaging.producer.ResultadoProducer;
import br.com.desafio.backvotos.infrastructure.pauta.mapper.PautaMapper;
import br.com.desafio.backvotos.infrastructure.resultado.mapper.ResultadoMapper;
import br.com.desafio.backvotos.infrastructure.resultado.persistence.ResultadoJpa;
import br.com.desafio.backvotos.infrastructure.resultado.persistence.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultadoGatewayImpl implements ResultadoGateway {

    @Autowired
    private ResultadoRepository repository;

    @Autowired
    private ResultadoProducer resultadoProducer;

    @Override
    public Resultado insert(Resultado resultado) {
        var contabilizacao = repository.listByIdPauta(resultado.getIdPauta());

        var sim = 0l;
        var nao = 0l;

        if(contabilizacao != null
                && contabilizacao.size() > 0) {
            var rSim = contabilizacao.get(0);
            var rNao = contabilizacao.get(1);

            if(contabilizacao.get(0) != null) {
                var agregateSim = ((Object[])rSim)[0].toString();
                var valueSim = ((Long) ((Object[])rSim)[1]).longValue();

                sim = valueSim;
            }

            if(contabilizacao.get(1) != null) {
                var agregateNao = ((Object[])rNao)[0].toString();
                var valueNao = ((Long) ((Object[])rNao)[1]).longValue();

                nao = valueNao;
            }
        }

        resultado.update(sim, nao);

        var persistence = repository.save(ResultadoMapper.toPersistence(resultado));
        return ResultadoMapper.toDomain(persistence);
    }

    @Override
    public Optional<Resultado> getByIdPauta(String idPauta) {
        return repository.findByIdPauta(idPauta).map(ResultadoMapper::toDomain);
    }

    @Override
    public void sendResult(String idPauta) {
        var resultado = repository.findByIdPauta(idPauta)
                .orElseThrow(() -> NotFoundException.with(Resultado.class, idPauta));
        resultadoProducer.send(this.construirMensagem(resultado));
    }

    private String construirMensagem(ResultadoJpa resultadoJpa) {
        String mensagem = "id_pauta = " + resultadoJpa.getIdPauta() + "; ";
        mensagem += "sim: " +resultadoJpa.getSim() + "; ";
        mensagem += "nao: " +resultadoJpa.getNao() + "; ";
        mensagem += "data_contabilização: " +resultadoJpa.getCreatedAt() + "; ";

        return mensagem;
    }
}
