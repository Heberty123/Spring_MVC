package br.com.Agencia.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Agencia.Models.Turismo;

@Repository
public interface TurismoRepository extends JpaRepository<Turismo, Long>{

}
