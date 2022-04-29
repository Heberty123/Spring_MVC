package transacao.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import transacao.Models.Transacao;

@Repository
public interface RepositoryTransacao extends JpaRepository<Transacao, Long> {

	@Query("SELECT t FROM Transacao t INNER JOIN t.importacao i WHERE i.id = :id")
	List<Transacao> findAllByIdOfImportacao(Long id);

}
