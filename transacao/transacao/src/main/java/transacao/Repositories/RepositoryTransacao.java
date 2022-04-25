package transacao.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import transacao.Models.Transacao;

@Repository
public interface RepositoryTransacao extends JpaRepository<Transacao, Long> {

}
