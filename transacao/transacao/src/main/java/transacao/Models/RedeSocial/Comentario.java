package transacao.Models.RedeSocial;

import transacao.Models.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Usuario usuario;
    private String subtitle;
    private String description;
    private Date date = new Date();

    public Comentario(){}

    public Comentario(Usuario usuario, String subtitle, String description) {
        this.usuario = usuario;
        this.subtitle = subtitle;
        this.description = description;
    }


    @OneToMany(mappedBy="comentario")
    private List<Love> likes = new ArrayList<Love>();

    @OneToMany(mappedBy="comentario")
    private List<Amazing> amazings = new ArrayList<Amazing>();

    @OneToMany(mappedBy="comentario")
    private List<Boring> borings = new ArrayList<Boring>();

    @OneToMany(mappedBy="comentario")
    private List<Angry> angrys = new ArrayList<Angry>();

    @OneToMany(mappedBy="comentario")
    private List<Disgust> disgusts = new ArrayList<Disgust>();


    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
