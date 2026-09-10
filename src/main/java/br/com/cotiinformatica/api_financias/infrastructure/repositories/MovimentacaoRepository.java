package br.com.cotiinformatica.api_financias.infrastructure.repositories;

import br.com.cotiinformatica.api_financias.domain.entities.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, UUID> {
}
