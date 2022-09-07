package br.com.desafio.backvotos.application;

public interface UseCase<IN, OUT> {
    OUT execute(IN in);
}
