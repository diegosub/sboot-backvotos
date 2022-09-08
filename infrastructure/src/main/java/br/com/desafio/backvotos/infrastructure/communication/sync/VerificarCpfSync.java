package br.com.desafio.backvotos.infrastructure.communication.sync;

import br.com.desafio.backvotos.domain.exception.DomainException;
import br.com.desafio.backvotos.infrastructure.voto.model.CpfResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class VerificarCpfSync {

    public static boolean verificarCpfValido(final String cpf) {
        boolean retorno = false;

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<CpfResponse> response = restTemplate.getForEntity("https://user-info.herokuapp.com/users/" + cpf, CpfResponse.class);

            if(response != null
                    && response.getBody() != null
                    && response.getBody().status() != null
                    && response.getBody().status().equals("ABLE_TO_VOTE")) {
                retorno = true;
            } else {
                retorno = false;
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw DomainException.with("Cpf invalido");
            } else {
                e.printStackTrace();
                throw DomainException.with("Erro ao validar CPF");
            }
        }

        return retorno;
    }

}
