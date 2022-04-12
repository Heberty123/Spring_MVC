package demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.Models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	
}
