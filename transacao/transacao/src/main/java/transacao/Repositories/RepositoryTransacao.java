package transacao.Repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import transacao.Models.Transacao;

@Repository
public interface RepositoryTransacao extends JpaRepository<Transacao, Long> {

	@Query("SELECT t FROM Transacao t INNER JOIN t.importacao i WHERE i.id = :id")
	List<Transacao> findAllByIdOfImportacao(Long id);

	@Query("SELECT t FROM Transacao t WHERE t.valor >= :value AND MONTH(t.data) = :month AND YEAR(t.data) = :year")
	List<Transacao> findAllSuspiciousTransactionsWithMonthAndYear(Double value, int month, int year);

	@Query("SELECT t FROM Transacao t WHERE t.contaOrigem = :sourceAccount")
	Set<Transacao> findAllBySourceAccountExit(String sourceAccount);
	
	@Query("SELECT t FROM Transacao t WHERE t.contaDestino = :destinationAccount")
	Set<Transacao> findAllBySourceAccountEntry(String destinationAccount);

	@Query("SELECT t FROM Transacao t WHERE MONTH(t.data) = :month AND YEAR(t.data) = :year")
	List<Transacao> findAllWithMonthAndYear(int month, int year);

}
