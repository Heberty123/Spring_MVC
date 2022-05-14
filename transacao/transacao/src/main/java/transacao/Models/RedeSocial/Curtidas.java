package transacao.Models.RedeSocial;

import transacao.Models.Usuario;

import javax.persistence.*;


@MappedSuperclass
public class Curtidas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Usuario usuario;

    @ManyToOne
    private Comentario comentario;

    public Curtidas(Long id, Usuario usuario, Comentario comentario) {
        this.id = id;
        this.usuario = usuario;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
}
