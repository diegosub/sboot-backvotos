package br.com.desafio.backvotos.infrastructure.api.v1.controller;

import br.com.desafio.backvotos.application.pauta.dto.CadastrarPautaInput;
import br.com.desafio.backvotos.application.pauta.dto.IniciarSessaoInput;
import br.com.desafio.backvotos.application.pauta.usecase.cadastrar.CadastrarPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.get.GetPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.iniciar.IniciarSessaoUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.pesquisar.PesquisarPautaUseCase;
import br.com.desafio.backvotos.domain.search.Pagination;
import br.com.desafio.backvotos.domain.search.SearchQuery;
import br.com.desafio.backvotos.infrastructure.api.v1.PautaApi;
import br.com.desafio.backvotos.infrastructure.pauta.mapper.PautaMapper;
import br.com.desafio.backvotos.infrastructure.pauta.model.CadastrarPautaRequest;
import br.com.desafio.backvotos.infrastructure.pauta.model.IniciarSessaoRequest;
import br.com.desafio.backvotos.infrastructure.pauta.model.PautaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class PautaController implements PautaApi {

    @Autowired
    private CadastrarPautaUseCase cadastrarPautaUseCase;

    @Autowired
    private IniciarSessaoUseCase iniciarSessaoUseCase;

    @Autowired
    private GetPautaUseCase getPautaUseCase;

    @Autowired
    private PesquisarPautaUseCase pesquisarPautaUseCase;

    @Override
    public ResponseEntity<PautaResponse> inserir(CadastrarPautaRequest payload) {
        var input = CadastrarPautaInput.create(payload.nome());
        var output = cadastrarPautaUseCase.execute(input);
        return ResponseEntity.created(URI.create("/pauta/" + output.id())).body(PautaMapper.toResponse(output));
    }

    @Override
    public ResponseEntity<PautaResponse> iniciarSessao(String id, IniciarSessaoRequest payload) {
        var input = IniciarSessaoInput.create(id, payload.minutos());
        var output = iniciarSessaoUseCase.execute(input);
        return ResponseEntity.ok(PautaMapper.toResponse(output));
    }

    @Override
    public ResponseEntity<PautaResponse> get(String id) {
        var output = getPautaUseCase.execute(id);
        return ResponseEntity.ok(PautaMapper.toResponse(output));
    }

    @Override
    public Pagination<PautaResponse> pesquisar(String nome, int page, int perPage, String sort, String direction) {
        return pesquisarPautaUseCase.execute(new SearchQuery(page, perPage, nome, sort, direction))
                .map(PautaMapper::toResponse);
    }

}
