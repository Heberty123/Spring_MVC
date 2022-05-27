package transacao.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import transacao.Models.RedeSocial.Comentario;


@Repository
public interface RepositoryComentarios extends JpaRepository<Comentario, Long> {
    @Query("DELETE FROM Curtidas c WHERE c.comentario.id = :id")
    public void deleteAllCurtidas(Long id);
}
