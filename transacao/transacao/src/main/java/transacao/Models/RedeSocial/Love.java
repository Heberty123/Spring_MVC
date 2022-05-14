package transacao.Models.RedeSocial;

import transacao.Models.Usuario;

import javax.persistence.Entity;

@Entity
public class Love extends Curtidas{


    public Love(Long id, Usuario usuario, Comentario comentario) {
        super(id, usuario, comentario);
    }
}
