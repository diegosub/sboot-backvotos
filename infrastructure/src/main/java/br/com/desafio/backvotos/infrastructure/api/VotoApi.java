package br.com.desafio.backvotos.infrastructure.api;

import br.com.desafio.backvotos.application.pauta.dto.CadastrarPautaInput;
import br.com.desafio.backvotos.application.pauta.dto.IniciarSessaoInput;
import br.com.desafio.backvotos.application.pauta.dto.PautaOutput;
import br.com.desafio.backvotos.domain.search.Pagination;
import br.com.desafio.backvotos.infrastructure.pauta.model.CadastrarPautaRequest;
import br.com.desafio.backvotos.infrastructure.pauta.model.IniciarSessaoRequest;
import br.com.desafio.backvotos.infrastructure.pauta.model.PautaResponse;
import br.com.desafio.backvotos.infrastructure.voto.model.CadastrarVotoRequest;
import br.com.desafio.backvotos.infrastructure.voto.model.VotoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "voto")
@Tag(name = "Voto")
public interface VotoApi {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Cadatrar um novo Voto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Voto criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Um erro de validação foi lançado"),
            @ApiResponse(responseCode = "500", description = "Um erro interno de servidor foi lançado"),
    })
    ResponseEntity<VotoResponse> inserir(@RequestBody CadastrarVotoRequest payload);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Obter um voto por seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voto recuperado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Voto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Um erro interno de servidor foi lançado"),
    })
    ResponseEntity<VotoResponse> get(@PathVariable(name = "id") String id);


}