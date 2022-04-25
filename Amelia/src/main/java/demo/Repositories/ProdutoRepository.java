package demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.Models.Cliente;
import demo.Models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT p FROM Produto p INNER JOIN p.clientes c WHERE c.id = :id")
	List<Produto> findAllProdutoByIdOfCliente(Long id);
	
	@Query("SELECT c FROM Cliente c INNER JOIN c.produtos p WHERE p.id = :id")
	List<Cliente> findAllClienteByIdOfProduto(Long id);

	
}
