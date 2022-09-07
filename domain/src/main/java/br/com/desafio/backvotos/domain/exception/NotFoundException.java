package br.com.desafio.backvotos.domain.exception;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends NoStackTraceException {
    protected NotFoundException(final String message) {
        super(message);
    }

    public static NotFoundException with(String message) {
        return new NotFoundException(message);
    }

    public static NotFoundException with(
            final Class<?> classe,
            final String id
    ) {
        final var error = "%s com o ID %s não foi encontrado".formatted(
                classe.getSimpleName(),
                id
        );
        return new NotFoundException(error);
    }
}