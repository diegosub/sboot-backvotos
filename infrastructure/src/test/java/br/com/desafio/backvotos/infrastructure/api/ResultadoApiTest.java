package br.com.desafio.backvotos.infrastructure.api;

import br.com.desafio.backvotos.application.resultado.dto.ResultadoInput;
import br.com.desafio.backvotos.application.resultado.dto.ResultadoOutput;
import br.com.desafio.backvotos.application.resultado.usecase.contabilizar.ContabilizarResultadoUseCase;
import br.com.desafio.backvotos.application.resultado.usecase.get.GetResultadoUseCase;
import br.com.desafio.backvotos.domain.resultado.Resultado;
import br.com.desafio.backvotos.infrastructure.ControllerTest;
import br.com.desafio.backvotos.infrastructure.api.v1.ResultadoApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ControllerTest(controllers = ResultadoApi.class)
public class ResultadoApiTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ContabilizarResultadoUseCase contabilizarResultadoUseCase;

    @MockBean
    private GetResultadoUseCase getResultadoUseCase;

    @Test
    public void givenAValidInput_whenCallsInserir_shouldReturnAValidResultado() throws Exception {
        // given
        final var idPauta = "a286d30a-2da0-11ed-a261-0242ac120002";

        var input = ResultadoInput.create(idPauta);
        var resultado = Resultado.create(idPauta);

        when(contabilizarResultadoUseCase.execute(any()))
                .thenReturn(ResultadoOutput.toOutput(resultado));

        // when
        final var request = post("/v1/resultado")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(input));

        final var response = this.mvc.perform(request)
                .andDo(print());

        // then
        response.andExpect(status().isCreated())
                .andExpect(header().string("Location", "/v1/resultado/" + resultado.getId()))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", equalTo(resultado.getId())));

    }

}
