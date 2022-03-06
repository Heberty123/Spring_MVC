package br.com.Agencia.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Agencia.Models.Continente;
import br.com.Agencia.Models.People;

public interface ContinenteRepository extends JpaRepository<Continente, Long> {

	
}
