package transacao.Service.Comentarios;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import transacao.DAO.RequestComentary;
import transacao.Models.RedeSocial.Comentario;
import transacao.Models.Usuario;
import transacao.Repositories.RepositoryComentarios;
import transacao.Repositories.RepositoryUser;
import transacao.Service.ConfigImg;

import java.security.Principal;
import java.util.Optional;

@Service @AllArgsConstructor
public class CatalogoComentary {

    private RepositoryComentarios repositoryComentarios;
    private RepositoryUser repositoryUser;

    private ConfigImg configImg;


    public void saveComm(Principal principal, RequestComentary req) throws Exception {
        Usuario usuario = repositoryUser.findByUsername(principal.getName());
        Comentario comentario = new Comentario(usuario, req.getSubtitle(), req.getDescription());
        repositoryComentarios.save(comentario);
    }

    public ModelAndView edit(Long id, Principal principal, RequestComentary req) {
        ModelAndView mv = new ModelAndView("RedeSocial/editComentario.html");
        Optional<Comentario> optional = repositoryComentarios.findById(id);
        Comentario comentario = optional.get();

        if(!(comentario.getUsuario().getNome().equals(principal.getName()))){
            return new ModelAndView("redirect:/Rede/Social");
        }
        req.setCom(comentario);
        mv.addObject("id", comentario.getId());
        return mv;
    }

    public ModelAndView delete(Long id, Principal principal) {
        Optional<Comentario> optional = repositoryComentarios.findById(id);
        Comentario comentario = optional.get();

        if(!(comentario.getUsuario().getNome().equals(principal.getName()))){
            return new ModelAndView("redirect:/Rede/Social");
        }
        repositoryComentarios.deleteById(id);
        return new ModelAndView("redirect:/Rede/Social");
    }

    public ModelAndView alter(Long id, Principal principal, RequestComentary req) {
        Comentario comentario = new Comentario();
        comentario.setId(id);
        comentario.setUsuario(repositoryUser.findByUsername(principal.getName()));
        comentario.setSubtitle(req.getSubtitle());
        comentario.setDescription(req.getDescription());
        repositoryComentarios.save(comentario);
        return new ModelAndView("redirect:/Rede/Social");
    }
}
