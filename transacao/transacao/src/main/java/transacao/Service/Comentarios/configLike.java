package transacao.Service.Comentarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import transacao.Models.RedeSocial.*;
import transacao.Models.Usuario;
import transacao.Repositories.*;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class configLike {
    @Autowired
    private RepositoryComentarios repositoryComentarios;
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private RepositoryCurtidas repositoryCurtida;


    public void persist(Long comentarioId, int curtidaId, Principal principal){


        Optional<Comentario> opt = repositoryComentarios.findById(comentarioId);
        Comentario comentario = opt.get();
        Usuario usuario = repositoryUser.findByUsername(principal.getName());
        Curtidas curtida = comentario.existCurtida(usuario.getUsername());



        if(curtida != null){
            if(curtida.getType() == curtidaId){
                repositoryCurtida.delete(curtida);
            }
            else {
                curtida.setType(curtidaId);
                repositoryCurtida.save(curtida);
            }
        }
        else{

            curtida = new Curtidas(null, usuario, comentario);

            switch (curtidaId){

                case 1:
                    curtida.setType(1);
                    break;
                case 2:
                    curtida.setType(2);
                    break;
                case 3:
                    curtida.setType(3);
                    break;
                case 4:
                    curtida.setType(4);
                    break;
                case 5:
                    curtida.setType(5);
                    break;
            }
            repositoryCurtida.save(curtida);
        }

    }

    public void show(Long comentarioId, ModelAndView mv) {
        List<Curtidas> curtidas = repositoryCurtida.findAllByCommentaryId(comentarioId);
        mv.addObject("likes", curtidas);
    }



}
