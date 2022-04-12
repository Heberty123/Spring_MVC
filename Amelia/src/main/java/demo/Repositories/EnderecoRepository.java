package demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.Models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
