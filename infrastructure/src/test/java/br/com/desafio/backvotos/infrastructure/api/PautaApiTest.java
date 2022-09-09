package br.com.desafio.backvotos.infrastructure.api;

import br.com.desafio.backvotos.application.pauta.dto.CadastrarPautaInput;
import br.com.desafio.backvotos.application.pauta.dto.PautaOutput;
import br.com.desafio.backvotos.application.pauta.usecase.cadastrar.CadastrarPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.get.GetPautaUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.iniciar.IniciarSessaoUseCase;
import br.com.desafio.backvotos.application.pauta.usecase.pesquisar.PesquisarPautaUseCase;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.infrastructure.ControllerTest;
import br.com.desafio.backvotos.infrastructure.api.PautaApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ControllerTest(controllers = PautaApi.class)
public class PautaApiTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CadastrarPautaUseCase cadastrarPautaUseCase;

    @MockBean
    private IniciarSessaoUseCase iniciarSessaoUseCase;

    @MockBean
    private GetPautaUseCase getPautaUseCase;

    @MockBean
    private PesquisarPautaUseCase pesquisarPautaUseCase;

    @Test
    public void givenAValidInput_whenCallsCadastrarPauta_shouldReturnAValidPauta() throws Exception {
        // given
        final var expectedName = "Pauta de Teste";
        final var input = new CadastrarPautaInput(expectedName);
        final var pautaReturn = Pauta.create("Pauta Return");

        when(cadastrarPautaUseCase.execute(any()))
                .thenReturn(PautaOutput.toOutput(pautaReturn));

        // when
        final var request = post("/pauta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(input));

        final var response = this.mvc.perform(request)
                .andDo(print());

        // then
        response.andExpect(status().isCreated())
                .andExpect(header().string("Location", "/pauta/" + pautaReturn.getId()))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", equalTo(pautaReturn.getId())));

        verify(cadastrarPautaUseCase, times(1)).execute(argThat(cmd ->
                Objects.equals(expectedName, cmd.nome())
        ));
    }

    @Test
    public void givenAValidInput_whenCallsIniciarSessao_shouldReturnAValidPauta() throws Exception {
        // given
        final var expectedName = "Pauta de Teste";
        final var input = new CadastrarPautaInput(expectedName);
        final var pautaReturn = Pauta.create("Pauta Return");

        when(cadastrarPautaUseCase.execute(any()))
                .thenReturn(PautaOutput.toOutput(pautaReturn));

        // when
        final var request = post("/pauta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(input));

        final var response = this.mvc.perform(request)
                .andDo(print());

        // then
        response.andExpect(status().isCreated())
                .andExpect(header().string("Location", "/pauta/" + pautaReturn.getId()))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", equalTo(pautaReturn.getId())));

        verify(cadastrarPautaUseCase, times(1)).execute(argThat(cmd ->
                Objects.equals(expectedName, cmd.nome())
        ));
    }
}
