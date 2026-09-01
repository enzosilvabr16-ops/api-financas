package br.com.cotiinformatica.api_financias.domain.entities;

import br.com.cotiinformatica.api_financias.domain.enums.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "MOVIMENTACAO")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NOME", length = 150, nullable = false, unique = true)
    private String descricao;

    @Column(name = "VALOR", precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "DATA_HORA", length = 100, nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "TIPO", length = 100, nullable = false)
    private TipoMovimentacao tipo;

    @ManyToMany
    @JoinTable(
            name = "MOVIMENTACOES_CATEGORIAS",
            joinColumns = @JoinColumn(name = "MOVIMENTACAO_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORIA_ID")
    )
    private List<Categoria> categorias;

}
