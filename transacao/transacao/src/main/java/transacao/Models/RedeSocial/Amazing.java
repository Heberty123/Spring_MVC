package transacao.Models.RedeSocial;

import transacao.Models.Usuario;
import javax.persistence.Entity;

@Entity
public class Amazing extends Curtidas{


    public Amazing(Long id, Usuario usuario, Comentario comentario) {
        super(id, usuario, comentario);
    }
}
