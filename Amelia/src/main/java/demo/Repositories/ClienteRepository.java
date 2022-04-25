package demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.Models.Cliente;
import demo.Models.Produto;

public interface ClienteRepository extends JpaRepository<Cliente, Long>  {
	
	Cliente findByCpf(Long cpf);
	
	@Query("SELECT u FROM Cliente u INNER JOIN u.enderecos e WHERE e.id = :id")
	Cliente findClienteByIdOfEndereco(Long id);
	
	@Query("SELECT u FROM Cliente u INNER JOIN u.produtos p WHERE p.id = :id")
	Cliente findClienteByIdOfProduto(Long id);

	@Query("SELECT p FROM Produto p INNER JOIN p.clientes c WHERE c.id = :id")
	List<Produto> findAllProdutoByIdOfCliente(Long id);
}
