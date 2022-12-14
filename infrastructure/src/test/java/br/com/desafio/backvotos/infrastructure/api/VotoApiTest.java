package br.com.desafio.backvotos.infrastructure.api;

import br.com.desafio.backvotos.application.voto.dto.CadastrarVotoInput;
import br.com.desafio.backvotos.application.voto.dto.VotoOutput;
import br.com.desafio.backvotos.application.voto.usecase.cadastrar.CadastrarVotoUseCase;
import br.com.desafio.backvotos.application.voto.usecase.get.GetVotoUseCase;
import br.com.desafio.backvotos.domain.enums.TipoVotoEnum;
import br.com.desafio.backvotos.domain.voto.Voto;
import br.com.desafio.backvotos.infrastructure.ControllerTest;
import br.com.desafio.backvotos.infrastructure.api.v1.VotoApi;
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

@ControllerTest(controllers = VotoApi.class)
public class VotoApiTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CadastrarVotoUseCase cadastrarVotoUseCase;

    @MockBean
    private GetVotoUseCase getVotoUseCase;

    @Test
    public void givenAValidInput_whenCallsCadastrarVoto_shouldReturnAValidVoto() throws Exception {
        // given
        final var idPauta = "a286d30a-2da0-11ed-a261-0242ac120002";
        final var cpf = "02345676590";
        final var valor = TipoVotoEnum.SIM;
        var input = CadastrarVotoInput.create(idPauta, cpf, valor);
        var voto = Voto.create(idPauta, cpf, valor);

        when(cadastrarVotoUseCase.execute(any()))
                .thenReturn(VotoOutput.toOutput(voto));

        // when
        final var request = post("/v1/voto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(input));

        final var response = this.mvc.perform(request)
                .andDo(print());

        // then
        response.andExpect(status().isCreated())
                .andExpect(header().string("Location", "/v1/voto/" + voto.getId()))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", equalTo(voto.getId())));
    }

}
