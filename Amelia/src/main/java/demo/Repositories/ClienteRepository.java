package demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.Models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>  {
	
	Cliente findByCpf(Long cpf);
}
