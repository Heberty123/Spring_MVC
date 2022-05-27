package transacao.Models.RedeSocial;

import lombok.Getter;
import lombok.Setter;
import transacao.Models.Usuario;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Curtidas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int type;
    @OneToOne
    private Usuario usuario;

    @ManyToOne
    private Comentario comentario;

    public Curtidas(){}

    public Curtidas(Long id, Usuario usuario, Comentario comentario) {
        this.id = id;
        this.usuario = usuario;
        this.comentario = comentario;
    }


    @Override
    public String toString() {
        return "Curtidas{" +
                "id=" + id +
                ", type=" + type +
                ", usuario=" + usuario +
                ", comentario=" + comentario +
                '}';
    }
}
