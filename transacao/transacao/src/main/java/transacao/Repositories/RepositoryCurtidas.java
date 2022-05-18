package transacao.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import transacao.Models.RedeSocial.Curtidas;

import java.util.List;

@Repository
public interface RepositoryCurtidas extends JpaRepository<Curtidas, Long> {
   @Query("SELECT l FROM Curtidas l INNER JOIN l.comentario c WHERE c.id = :comentarioId")
    List<Curtidas> findAllByCommentaryId(Long comentarioId);


}
