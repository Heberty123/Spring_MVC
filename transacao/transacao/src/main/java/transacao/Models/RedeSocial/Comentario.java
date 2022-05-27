package transacao.Models.RedeSocial;


import lombok.*;
import org.hibernate.annotations.Type;
import transacao.Models.Usuario;
import transacao.Service.ConfigImg;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Usuario usuario;
    private String subtitle;
    @Lob
    private String description;

    private ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));



    @OneToMany(mappedBy="comentario", cascade = CascadeType.REMOVE)
    private List<Curtidas> likes = new ArrayList<Curtidas>();

    public Comentario(){}

    public Comentario(Usuario usuario, String subtitle, String description) {
        this.usuario = usuario;
        this.subtitle = subtitle;
        this.description = description;
    }

    public int countLikes(){
        return likes.size();
    }


    public Curtidas existCurtida(String nome) {
        for (Curtidas like : likes) {
            if(like.getUsuario().getNome().equals(nome)){
                return like;
            }
        }
        return null;
    }


}
