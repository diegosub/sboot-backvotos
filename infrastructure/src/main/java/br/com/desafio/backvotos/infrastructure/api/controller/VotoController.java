package br.com.desafio.backvotos.infrastructure.api.controller;

import br.com.desafio.backvotos.application.pauta.dto.CadastrarPautaInput;
import br.com.desafio.backvotos.application.pauta.dto.IniciarSessaoInput;
import br.com.desafio.backvotos.application.pauta.usecase.cadastrar.CadastrarPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.get.GetPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.iniciar.IniciarSessaoUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.pesquisar.PesquisarPautaUseCase;
import br.com.desafio.backvotos.application.voto.dto.CadastrarVotoInput;
import br.com.desafio.backvotos.application.voto.usecase.cadastrar.CadastrarVotoUseCase;
import br.com.desafio.backvotos.application.voto.usecase.get.GetVotoUseCase;
import br.com.desafio.backvotos.domain.search.Pagination;
import br.com.desafio.backvotos.domain.search.SearchQuery;
import br.com.desafio.backvotos.infrastructure.api.PautaApi;
import br.com.desafio.backvotos.infrastructure.api.VotoApi;
import br.com.desafio.backvotos.infrastructure.pauta.mapper.PautaMapper;
import br.com.desafio.backvotos.infrastructure.pauta.model.CadastrarPautaRequest;
import br.com.desafio.backvotos.infrastructure.pauta.model.IniciarSessaoRequest;
import br.com.desafio.backvotos.infrastructure.pauta.model.PautaResponse;
import br.com.desafio.backvotos.infrastructure.voto.mapper.VotoMapper;
import br.com.desafio.backvotos.infrastructure.voto.model.CadastrarVotoRequest;
import br.com.desafio.backvotos.infrastructure.voto.model.VotoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class VotoController implements VotoApi {

    @Autowired
    private CadastrarVotoUseCase cadastrarVotoUseCase;

    @Autowired
    private GetVotoUseCase getVotoUseCase;

    @Override
    public ResponseEntity<VotoResponse> inserir(CadastrarVotoRequest payload) {
        var input = CadastrarVotoInput.create(
                payload.idPauta(),
                payload.cpf(),
                payload.valor()
        );
        var output = cadastrarVotoUseCase.execute(input);
        return ResponseEntity.created(URI.create("/voto/" + output.id())).body(VotoMapper.toResponse(output));
    }

    @Override
    public ResponseEntity<VotoResponse> get(String id) {
        var output = getVotoUseCase.execute(id);
        return ResponseEntity.ok(VotoMapper.toResponse(output));
    }

}
