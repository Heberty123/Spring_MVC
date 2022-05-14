package transacao.Models.RedeSocial;

import transacao.Models.Usuario;

import javax.persistence.Entity;

@Entity
public class Boring extends Curtidas{


    public Boring(Long id, Usuario usuario, Comentario comentario) {
        super(id, usuario, comentario);
    }
}
