package br.com.Agencia.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.Agencia.Models.Turismo;

@Repository
public interface TurismoRepository extends JpaRepository<Turismo, Long>{

	
	@Query("SELECT u FROM Turismo u INNER JOIN u.peoples p WHERE p.id = :id")
    Turismo findTurismoByIdOfPeople(Long id);
}
