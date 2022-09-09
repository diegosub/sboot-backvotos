package br.com.desafio.backvotos.infrastructure.api.v1;

import br.com.desafio.backvotos.domain.search.Pagination;
import br.com.desafio.backvotos.infrastructure.pauta.model.CadastrarPautaRequest;
import br.com.desafio.backvotos.infrastructure.pauta.model.IniciarSessaoRequest;
import br.com.desafio.backvotos.infrastructure.pauta.model.PautaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/v1/pauta")
@Tag(name = "Pauta")
public interface PautaApi {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Criar uma nova Pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Um erro de validação foi lançado"),
            @ApiResponse(responseCode = "500", description = "Um erro interno de servidor foi lançado"),
    })
    ResponseEntity<PautaResponse> inserir(@RequestBody CadastrarPautaRequest payload);

    @PutMapping(
            value = "{id}/iniciarSessao",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Iniciar uma sessão de votação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão iniciada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Um erro de validação foi lançado"),
            @ApiResponse(responseCode = "500", description = "Um erro interno de servidor foi lançado"),
    })
    ResponseEntity<PautaResponse> iniciarSessao(@PathVariable(name = "id") String id, @RequestBody IniciarSessaoRequest payload);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Obter uma pauta por seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pauta recuperada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada"),
            @ApiResponse(responseCode = "500", description = "Um erro interno de servidor foi lançado"),
    })
    ResponseEntity<PautaResponse> get(@PathVariable(name = "id") String id);

    @GetMapping
    @Operation(summary = "Lista todas as pautas com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Um parametro invalido foi enviado"),
            @ApiResponse(responseCode = "500", description = "Um erro interno de servidor foi lançado"),
    })
    Pagination<PautaResponse> pesquisar(
            @RequestParam(name = "nome", required = false, defaultValue = "") final String nome,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "nome") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    );

}