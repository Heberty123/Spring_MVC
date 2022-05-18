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
import transacao.Repositories.*;
import transacao.Service.Comentarios.configLike;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/Rede")
public class RedeSocialController {

    @Autowired
    private RepositoryComentarios repositoryComentarios;
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private configLike serLike;

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


    @RequestMapping("/Close")
    public ModelAndView Close(){
        ModelAndView mv = new ModelAndView("RedeSocial/redeSocial.html");
        List<Comentario> comentarios = repositoryComentarios.findAll();
        mv.addObject("comentarios", comentarios);
        mv.addObject("open", false);


        return mv;
    }



    @RequestMapping("/Perfil")
    public ModelAndView Perfil(Principal principal){
        ModelAndView mv = new ModelAndView("Autenticacao/perfil.html");
        Usuario usuario = repositoryUser.findByUsername(principal.getName());
        mv.addObject("usuario", usuario);

        Base64.Encoder encoder = Base64.getEncoder();

        if(usuario.getImagem() != null){
            String encoding = "data:image/png;base64," + encoder.encodeToString(usuario.getImagem());
            mv.addObject("encoding", encoding);
        }
        else{
            mv.addObject("encoding", "nothing");
        }

        return mv;
    }

        @PostMapping("/Perfil")
        public ModelAndView PPerfil(@RequestParam("inpimage") MultipartFile file, Principal principal) throws IOException {
        ModelAndView mv = new ModelAndView("redirect:/Rede/Perfil");
        Usuario usuario = repositoryUser.findByUsername(principal.getName());
        mv.addObject("usuario", usuario);


        InputStream input = file.getInputStream();

        BufferedImage teste = ImageIO.read(input);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(teste, "png", baos);
        byte[] bytess = baos.toByteArray();

        usuario.setImagem(bytess);

        repositoryUser.save(usuario);



        return mv;
    }

}
