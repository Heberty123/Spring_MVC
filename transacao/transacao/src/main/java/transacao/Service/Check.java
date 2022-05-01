package transacao.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import transacao.Models.Transacao;
import transacao.Models.Usuario;

public class Check {
	
	public static List<Transacao> findErroByDate(List<Transacao> lista) {
		List<Transacao> listaDateErro = new ArrayList<>();
		Date primeiraData = lista.get(0).getData();
		
		for (Transacao transacao : lista) {
			
			if(transacao.getData().getDay() != primeiraData.getDay() || transacao.getData().getMonth() != primeiraData.getMonth() || transacao.getData().getYear() != primeiraData.getYear()) {
				listaDateErro.add(transacao);
			}
		}
		
		return listaDateErro;
	}
	
	
	public static List<Transacao> findErroNull(List<Transacao> lista){
		List<Transacao> listaErroNull = new ArrayList<>();
		
		for (Transacao transacao : lista) {
			if(transacao.hasNull()) {
				listaErroNull.add(transacao);
			}
		}
		return listaErroNull;
	}
	
	
	public static ModelAndView HasErro(Usuario UsernameExist, Usuario EmailExist) {
		ModelAndView mv = new ModelAndView("redirect:/login");
		if(UsernameExist != null || EmailExist != null) {
			mv = new ModelAndView("Autenticacao/cadastro.html");
			
			if(UsernameExist != null){
				mv.addObject("erroUsername", true);
				System.out.println("tinha erro de username");
			}
			if(EmailExist != null) {
				mv.addObject("erroEmail", true);
				System.out.println("tinah erro de email");
			}
			return mv;
		}
		
		return mv;
	}
}
