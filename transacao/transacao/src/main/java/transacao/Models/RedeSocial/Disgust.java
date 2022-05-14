package transacao.Models.RedeSocial;

import transacao.Models.Usuario;

import javax.persistence.Entity;

@Entity
public class Disgust extends Curtidas{


    public Disgust(Long id, Usuario usuario, Comentario comentario) {
        super(id, usuario, comentario);
    }
}
