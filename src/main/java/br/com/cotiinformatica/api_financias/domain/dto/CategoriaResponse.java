package br.com.cotiinformatica.api_financias.domain.dto;

import java.util.UUID;

public record CategoriaResponse(
        UUID id,
        String nome
) {
}
