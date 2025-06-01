package br.com.fiap.gs.repository;


import br.com.fiap.gs.model.entities.Necessidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NecessidadeRepository extends JpaRepository<Necessidade, Long> {
}
