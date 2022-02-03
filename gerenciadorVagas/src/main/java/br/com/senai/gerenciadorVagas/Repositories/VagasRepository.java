package br.com.senai.gerenciadorVagas.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.senai.gerenciadorVagas.Models.Vagas;

@Repository
public interface VagasRepository extends JpaRepository<Vagas, Long> {

}
