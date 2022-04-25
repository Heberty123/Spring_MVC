package transacao.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.web.multipart.MultipartFile;

import transacao.Models.Transacao;

public class ReadFile {
	
	public static Map Ready(MultipartFile file){
		List<Transacao> lista = new ArrayList<>();
		List<Transacao> duplicadas = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		
		try {
			InputStream input = file.getInputStream();
			
			try(InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8)){
				
				try(BufferedReader br = new BufferedReader(isr)){
					
					br.lines().forEach(line -> {
						String fields[] = line.split(",");
						try {
							Transacao transacao = new Transacao(fields[0], Convert.toInteger(fields[1]), fields[2], fields[3], Convert.toInteger(fields[4]), fields[5], Convert.toDouble(fields[6]), format.parse(fields[7]));
							if(lista.contains(transacao)) {
								duplicadas.add(transacao);
							}
							else {
								lista.add(transacao);
							}
						} catch (Exception e) {
							
							e.printStackTrace();
						} 
					});
				}
				
			}
			input.close();		
	
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		
		List<Transacao> listaErroNull = Check.findErroNull(lista);
		
		List<Transacao> listaErroDate = Check.findErroByDate(lista);
		
		List<Transacao> novalista = endList(lista, listaErroNull, listaErroDate);
	
		Map map = Map.of("lista", novalista, "duplicidades", duplicadas, "erroNull", listaErroNull, "erroDate", listaErroDate);
		return map;
	}
	
	
	
	
	public static List<Transacao> endList(List<Transacao> lista, List<Transacao> ErroNull, List<Transacao> ErroDate){
		
		lista.removeAll(ErroNull);
		lista.removeAll(ErroDate);
		
		return lista;
	}


	
	public static boolean fileIsEmpty(MultipartFile file) {
		
		try {
			InputStream input = file.getInputStream();
			
			try(InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8)){
					
					Scanner sc = new Scanner(isr);
					sc.next();
					if(sc.hasNext()) {
						return false;
					}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		return true; 
	
	}
	
}
