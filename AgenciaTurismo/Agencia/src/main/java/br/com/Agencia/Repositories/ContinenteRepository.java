package br.com.Agencia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.com.Agencia.Models.Continente;
import br.com.Agencia.Models.Turismo;

public interface ContinenteRepository extends JpaRepository<Continente, Long> {

	@Query("SELECT u FROM Continente u WHERE u.nome = :nome")
    Continente findContinenteByName(String nome);
	
	
	@Query("SELECT u.id, u.nome, u.data_initial, u.data_final, u.descricao, u.salario, c.nome FROM Turismo u INNER JOIN u.continente c WHERE c.nome LIKE %?1%")
    List<String> findContinentesWithManyTurismoByName(String nome);

}
