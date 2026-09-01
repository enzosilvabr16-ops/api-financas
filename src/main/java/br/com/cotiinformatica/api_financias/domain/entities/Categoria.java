package br.com.cotiinformatica.api_financias.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "CATEGORIAS")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "NOME", length = 50, nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    private List<Movimentacao> movimentacoes;
}