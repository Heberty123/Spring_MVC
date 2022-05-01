package transacao.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import transacao.Models.Transacao;
import transacao.Models.SuspiciousAccount;
import transacao.Repositories.RepositoryTransacao;

public class suspiciousTransaction {

	public static List<SuspiciousAccount> Account(List<Transacao> lista, RepositoryTransacao repositoryTransacao, Double valueLimit, String type) {

		Set<String> sourceAccount = getAllSourceAccountExit(lista);
		Set<String> destinationAccount = getAllSourceAccountEntry(lista);
		Map<String, Set<Transacao>> mapExit = getMapAllTransactionsByStringExit(sourceAccount, repositoryTransacao);
		Map<String, Set<Transacao>> mapEntry = getMapAllTransactionsByStringEntry(destinationAccount, repositoryTransacao);
		List<SuspiciousAccount> suspicious = findSuspiciousAccount(mapExit, mapEntry, valueLimit);
		
		return suspicious;
	}
	
	

	private static List<SuspiciousAccount> findSuspiciousAccount(Map<String, Set<Transacao>> mapExit, Map<String, Set<Transacao>> mapEntry, Double valueLimit){
		List<SuspiciousAccount> suspicious = new ArrayList<>();
		SuspiciousAccount account = new SuspiciousAccount();
		
		mapExit.forEach((k, v) -> {
			account.setConta(k);
			account.setTipo("saída");
			v.forEach(valor -> {
				account.setBanco(valor.getBancoOrigem());
				account.setAgencia(valor.getAgenciaOrigem());
				account.sum(valor.getValor());		
			});
			
			if(account.getValor() >= valueLimit) {
				suspicious.add(new SuspiciousAccount(account.getBanco(), account.getAgencia(), k, account.getValor(), account.getTipo()));
			}
			
			account.setValor(0.0);
		});
		
		
		mapEntry.forEach((k, v) -> {
			account.setConta(k);
			account.setTipo("entrada");
			v.forEach(valor -> {
				account.setBanco(valor.getBancoOrigem());
				account.setAgencia(valor.getAgenciaOrigem());
				account.sum(valor.getValor());		
			});
			
			if(account.getValor() >= valueLimit) {
				suspicious.add(new SuspiciousAccount(account.getBanco(), account.getAgencia(), k, account.getValor(), account.getTipo()));
			}
			
			account.setValor(0.0);
		});
		
		return suspicious;
	}
	
	

	private static Map<String, Set<Transacao>> getMapAllTransactionsByStringExit(Set<String> sourceAccount,
			RepositoryTransacao repositoryTransacao) {
		
		Map<String, Set<Transacao>> map = new HashMap<>();
		
		sourceAccount.forEach(Account -> {
			map.put(Account, repositoryTransacao.findAllBySourceAccountExit(Account));
		});
		
		return map;
	}
	
	
	
	private static Map<String, Set<Transacao>> getMapAllTransactionsByStringEntry(Set<String> destinationAccount,
			RepositoryTransacao repositoryTransacao) {
		
		Map<String, Set<Transacao>> map = new HashMap<>();
		
		destinationAccount.forEach(Account -> {
			map.put(Account, repositoryTransacao.findAllBySourceAccountEntry(Account));
		});
		
		return map;
	}




	private static Set<String> getAllSourceAccountExit(List<Transacao> lista) {
		Set<String> set = new HashSet<String>();
		for (Transacao transacao : lista) {
			set.add(transacao.getContaOrigem());
		}
		return set;
	}
	
	
	private static Set<String> getAllSourceAccountEntry(List<Transacao> lista) {
		Set<String> set = new HashSet<String>();
		for (Transacao transacao : lista) {
			set.add(transacao.getContaDestino());
		}
		return set;
	}
	
	

}
