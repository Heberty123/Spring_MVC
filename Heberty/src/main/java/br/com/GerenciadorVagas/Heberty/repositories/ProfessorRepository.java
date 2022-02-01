package br.com.GerenciadorVagas.Heberty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.GerenciadorVagas.Heberty.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

	
}
