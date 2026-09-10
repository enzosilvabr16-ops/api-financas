package br.com.cotiinformatica.api_financias.domain.dto;

import br.com.cotiinformatica.api_financias.domain.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record MovimentacaoResponse(
        UUID id,
        String descricao,
        Double valor,
        LocalDateTime dataHora,
        String tipo,
        List<CategoriaResponse> categorias
) {
}
