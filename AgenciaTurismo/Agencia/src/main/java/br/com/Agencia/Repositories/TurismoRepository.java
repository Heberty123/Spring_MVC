package br.com.Agencia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.Agencia.Models.Turismo;

@Repository
public interface TurismoRepository extends JpaRepository<Turismo, Long>{

	
	@Query("SELECT u FROM Turismo u INNER JOIN u.peoples p WHERE p.id = :id")
    Turismo findTurismoByIdOfPeople(Long id);
	
	@Query("SELECT u.id, u.nome, c.nome FROM Turismo u INNER JOIN u.continente c")
    List<String[]> findTurismoAllWithContinente();
}
