package transacao.DAO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import transacao.Models.RedeSocial.Comentario;

@Getter @Setter
public class RequestComentary {

    private String subtitle;

    private String description;

    public void setCom(Comentario comentario) {
        this.subtitle = comentario.getSubtitle();
        this.description = comentario.getDescription();
    }
}
