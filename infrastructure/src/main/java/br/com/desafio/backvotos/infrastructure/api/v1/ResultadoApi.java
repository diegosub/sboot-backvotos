package br.com.desafio.backvotos.infrastructure.api;

import br.com.desafio.backvotos.infrastructure.resultado.model.ResultadoRequest;
import br.com.desafio.backvotos.infrastructure.resultado.model.ResultadoResponse;
import br.com.desafio.backvotos.infrastructure.voto.model.CadastrarVotoRequest;
import br.com.desafio.backvotos.infrastructure.voto.model.VotoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "resultado")
@Tag(name = "Resultado")
public interface ResultadoApi {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Contabilizar o resultado de uma pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Resultado contabilizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Um erro de validação foi lançado"),
            @ApiResponse(responseCode = "500", description = "Um erro interno de servidor foi lançado"),
    })
    ResponseEntity<ResultadoResponse> inserir(@RequestBody ResultadoRequest payload);

    @GetMapping(
            value = "{idPauta}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Obter um resultado pelo identificador da pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado recuperado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Resultado não encontrado"),
            @ApiResponse(responseCode = "500", description = "Um erro interno de servidor foi lançado"),
    })
    ResponseEntity<ResultadoResponse> get(@PathVariable(name = "idPauta") String idPauta);


}