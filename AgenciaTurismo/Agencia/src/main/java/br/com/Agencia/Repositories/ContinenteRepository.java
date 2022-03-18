package br.com.Agencia.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.com.Agencia.Models.Continente;
import br.com.Agencia.Models.Turismo;

public interface ContinenteRepository extends JpaRepository<Continente, Long> {

	@Query("SELECT u FROM Continente u WHERE u.nome = :nome")
    Continente findContinenteByName(String nome);
}
