package transacao.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import transacao.DAO.RequestComentary;
import transacao.Models.RedeSocial.Comentario;
import transacao.Models.Usuario;
import transacao.Repositories.*;
import transacao.Service.Comentarios.CatalogoComentary;
import transacao.Service.Comentarios.configLike;
import transacao.Service.ConfigImg;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Controller
@RequestMapping("/Rede")
public class RedeSocialController {

    @Autowired
    private RepositoryComentarios repositoryComentarios;
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private configLike serLike;
    @Autowired
    private ConfigImg configImg;

    @Autowired
    private CatalogoComentary catalogoComentary;

    @RequestMapping("/Social")
    public ModelAndView Home(Principal principal){
        ModelAndView mv = new ModelAndView("RedeSocial/redeSocial.html");
        List<Comentario> comentarios = repositoryComentarios.findAll();
        mv.addObject("comentarios", comentarios);
        mv.addObject("user", principal.getName());

        return mv;
    }

    @RequestMapping("/Comentario")
    public ModelAndView Comentario(Principal principal, RequestComentary req){
        ModelAndView mv = new ModelAndView("RedeSocial/formComentario.html");
        mv.addObject("usuario", repositoryUser.findByUsername(principal.getName()));

        return mv;
    }

    @PostMapping("/Comentario")
    public ModelAndView SalvarComm(Principal principal, @Valid RequestComentary req) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/Rede/Social");
        catalogoComentary.saveComm(principal, req);

        return mv;
    }

    @GetMapping("/Comentario/edit/{id}")
    public ModelAndView edit(@PathVariable Long id, Principal principal, RequestComentary requestComentary) throws Exception {
        return catalogoComentary.edit(id, principal, requestComentary);
    }

    @PostMapping("/Comentario/edit/{id}")
    public ModelAndView alter(@PathVariable Long id, Principal principal, @Valid RequestComentary requestComentary) throws Exception {
        return catalogoComentary.alter(id, principal, requestComentary);
    }


    @GetMapping("/Comentario/delete/{id}")
    public ModelAndView delete(@PathVariable Long id, Principal principal) throws Exception {
        return catalogoComentary.delete(id, principal);
    }


    @RequestMapping("/Curtidas/{curtidaId}/Comentario/{comentarioId}")
    public ModelAndView Curtidas(@PathVariable Long comentarioId, @PathVariable int curtidaId, Principal principal){
        ModelAndView mv = new ModelAndView("redirect:/Rede/Social");
        serLike.persist(comentarioId, curtidaId, principal);


        return mv;
    }

    @RequestMapping("/Show/Curtidas/{ComentarioId}")
    public ModelAndView Show(@PathVariable Long ComentarioId){
        ModelAndView mv = new ModelAndView("RedeSocial/redeSocial.html");
        List<Comentario> comentarios = repositoryComentarios.findAll();
        mv.addObject("comentarios", comentarios);
        mv.addObject("open", true);
        serLike.show(ComentarioId, mv);


        return mv;
    }



    @RequestMapping("/Perfil")
    public ModelAndView Perfil(Principal principal){
        ModelAndView mv = new ModelAndView("Autenticacao/perfil.html");
        Usuario usuario = repositoryUser.findByUsername(principal.getName());
        mv.addObject("usuario", usuario);

        return mv;
    }

        @PostMapping("/Perfil")
        public ModelAndView PPerfil(@RequestParam("inpimage") MultipartFile file, Principal principal)  {
        ModelAndView mv = new ModelAndView("redirect:/Rede/Perfil");
        Usuario usuario = repositoryUser.findByUsername(principal.getName());
        mv.addObject("usuario", usuario);

        try{
            configImg.changeImg(file, usuario);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }


        repositoryUser.save(usuario);



        return mv;
    }

}
