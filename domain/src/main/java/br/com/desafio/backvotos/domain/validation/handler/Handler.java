package br.com.desafio.backvotos.domain.validation.handler;

import java.util.List;

public interface Handler {

    void append(String error);

    List<String> getErrors();

}