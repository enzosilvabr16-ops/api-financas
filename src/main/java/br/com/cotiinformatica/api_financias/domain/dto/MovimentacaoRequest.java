package br.com.cotiinformatica.api_financias.domain.dto;

import br.com.cotiinformatica.api_financias.domain.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record MovimentacaoRequest(
        String descricao,
        Double valor,
        LocalDateTime dataHora,
        String tipo,
        UUID[] categoriasId

) {
}
