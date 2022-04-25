package transacao.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import transacao.Models.Transacao;

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
}
