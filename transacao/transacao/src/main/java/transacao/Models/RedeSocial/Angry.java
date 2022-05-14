package transacao.Models.RedeSocial;

import transacao.Models.Usuario;
import javax.persistence.Entity;

@Entity
public class Angry extends Curtidas{


    public Angry(Long id, Usuario usuario, Comentario comentario) {
        super(id, usuario, comentario);
    }
}
