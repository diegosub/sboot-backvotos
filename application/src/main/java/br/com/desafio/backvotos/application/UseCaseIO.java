package br.com.desafio.backvotos.application;

public interface UseCaseIO<IN, OUT> {
    OUT execute(IN in);
}