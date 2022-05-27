package transacao.Service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import transacao.Models.Usuario;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;

@Service
public class ConfigImg {

    public void changeImg(MultipartFile file, Usuario usuario) throws Exception {
        InputStream input = file.getInputStream();

        BufferedImage teste = ImageIO.read(input);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            ImageIO.write(teste, "jpg", baos);
        }catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Don't was a correct image !");
        }

        byte[] bytess = baos.toByteArray();

        usuario.setImage(bytess);
    }



    public static void attributeImg(Usuario usuario) throws Exception {

        InputStream resource = new ClassPathResource("static/img/user.png").getInputStream();

        BufferedImage teste = ImageIO.read(resource);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try{
            ImageIO.write(teste, "png", baos);
        }catch(IllegalArgumentException e) {

            throw new IllegalArgumentException(e.getMessage());
        }

        byte[] bytess = baos.toByteArray();

        usuario.setImage(bytess);
    }


    public static String imgToModel(Usuario ent) {
        Base64.Encoder encoder = Base64.getEncoder();

        if (ent.getImage() != null) {
            return "data:image/png;base64," + encoder.encodeToString(ent.getImage());
        } else {
            return "nothing";
        }
    }



}
