package transacao.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import transacao.Models.Usuario;

@Repository
public interface RepositoryUser extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);

	Usuario findByEmail(String email);
}
