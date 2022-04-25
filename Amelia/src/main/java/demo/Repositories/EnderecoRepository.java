package demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.Models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Query("SELECT u FROM Endereco u INNER JOIN u.cliente c WHERE c.id = :id")
    List<Endereco> findAllEnderecoByIdOfCliente(Long id);
}
