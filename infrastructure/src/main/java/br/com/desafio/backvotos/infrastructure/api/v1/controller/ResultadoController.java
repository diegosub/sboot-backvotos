package br.com.desafio.backvotos.infrastructure.api.v1.controller;

import br.com.desafio.backvotos.application.resultado.dto.ResultadoInput;
import br.com.desafio.backvotos.application.resultado.usecase.contabilizar.ContabilizarResultadoUseCase;
import br.com.desafio.backvotos.application.resultado.usecase.get.GetResultadoUseCase;
import br.com.desafio.backvotos.infrastructure.api.v1.ResultadoApi;
import br.com.desafio.backvotos.infrastructure.resultado.mapper.ResultadoMapper;
import br.com.desafio.backvotos.infrastructure.resultado.model.ResultadoRequest;
import br.com.desafio.backvotos.infrastructure.resultado.model.ResultadoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ResultadoController implements ResultadoApi {

    @Autowired
    private ContabilizarResultadoUseCase contabilizarResultadoUseCase;

    @Autowired
    private GetResultadoUseCase getResultadoUseCase;

    @Override
    public ResponseEntity<ResultadoResponse> inserir(ResultadoRequest payload) {
        var input = ResultadoInput.create(
                payload.idPauta()
        );
        var output = contabilizarResultadoUseCase.execute(input);
        return ResponseEntity.created(URI.create("/v1/resultado/" + output.id())).body(ResultadoMapper.toResponse(output));
    }

    @Override
    public ResponseEntity<ResultadoResponse> get(String idPauta) {
        var output = getResultadoUseCase.execute(idPauta);
        return ResponseEntity.ok(ResultadoMapper.toResponse(output));
    }

}
