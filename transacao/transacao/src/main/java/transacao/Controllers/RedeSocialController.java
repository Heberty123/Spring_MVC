package transacao.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import transacao.Models.RedeSocial.Comentario;
import transacao.Models.Usuario;
import transacao.Repositories.RepositoryComentarios;
import transacao.Repositories.RepositoryUser;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/Rede")
public class RedeSocialController {

    @Autowired
    private RepositoryComentarios repositoryComentarios;

    @Autowired
    private RepositoryUser repositoryUser;

    @RequestMapping("/Social")
    public ModelAndView Home(){
        ModelAndView mv = new ModelAndView("RedeSocial/redeSocial.html");
        List<Comentario> comentarios = repositoryComentarios.findAll();
        mv.addObject("comentarios", comentarios);

        return mv;
    }

    @RequestMapping("/Comentario")
    public ModelAndView Comentario(Principal principal){
        ModelAndView mv = new ModelAndView("RedeSocial/formComentario.html");
        Usuario usuario = repositoryUser.findByUsername(principal.getName());
        mv.addObject("usuario", usuario);

        return mv;
    }

    @PostMapping("/Comentario")
    public ModelAndView SalvarComm(Principal principal, @RequestParam("subtitle") String subtitle, @RequestParam("description") String description){
        ModelAndView mv = new ModelAndView("redirect:/Rede/Social");
        Usuario usuario = repositoryUser.findByUsername(principal.getName());

        Comentario comentario = new Comentario(usuario, subtitle, description);
        repositoryComentarios.save(comentario);

        return mv;
    }



    @RequestMapping("/Curtidas/{id}")
    public ModelAndView Curtidas(@PathVariable Long id){
        ModelAndView mv = new ModelAndView("redirect:/Social");



        return mv;
    }
}
