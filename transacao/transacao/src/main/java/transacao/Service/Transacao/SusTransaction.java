package transacao.Service.Transacao;

import java.time.YearMonth;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import transacao.Models.SuspiciousAccount;
import transacao.Models.SuspiciousAgency;
import transacao.Repositories.RepositoryTransacao;

@Service
public class SusTransaction {

	@Autowired
	RepositoryTransacao repTransacao;

	public void showSuspicious(YearMonth local, ModelAndView mv) {

		int month = local.getMonthValue();
		int year = local.getYear();

		mv.addObject("SusTransacao", repTransacao.findAllSusTransactions(100000.00, month, year));

		mv.addObject("SusAccount", getAllSusAccont(month, year, 1000000.00));

		mv.addObject("SusAgency", getAllSusAgency(month, year, 1000000000.00));


	}



	public List<SuspiciousAccount> getAllSusAccont(int month, int year, double amount){

		List<SuspiciousAccount> list = repTransacao.OrigemAcc(month, year).stream()
				.map(sus -> sus.split(","))
				.map(sus -> new SuspiciousAccount(sus[0], Integer.parseInt(sus[1]), sus[2], Double.parseDouble(sus[3]), "exit"))
				.filter(sus -> sus.getValor() >= amount)
				.collect(Collectors.toList());

		list.addAll(repTransacao.DestinoAcc(month, year).stream()
				.map(sus -> sus.split(","))
				.map(sus -> new SuspiciousAccount(sus[0], Integer.parseInt(sus[1]), sus[2], Double.parseDouble(sus[3]), "Prohibited"))
				.filter(sus -> sus.getValor() >= amount)
				.collect(Collectors.toList()));

		return list;
	}


	public List<SuspiciousAgency> getAllSusAgency(int month, int year, double amount){

		List<SuspiciousAgency> list = repTransacao.OrigemAge(month, year).stream()
				.map(sus -> sus.split(","))
				.map(sus -> new SuspiciousAgency(sus[0], Integer.parseInt(sus[1]), Double.parseDouble(sus[2]), "exit"))
				.filter(sus -> sus.getValor() >= amount)
				.collect(Collectors.toList());

		list.addAll(repTransacao.DestinoAge(month, year).stream()
				.map(sus -> sus.split(","))
				.map(sus -> new SuspiciousAgency(sus[0], Integer.parseInt(sus[1]), Double.parseDouble(sus[2]), "Prohibited"))
				.filter(sus -> sus.getValor() >= amount)
				.collect(Collectors.toList()));

		return list;
	}


}
