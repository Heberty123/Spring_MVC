package br.com.Agencia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.Agencia.Models.People;
import br.com.Agencia.Models.Turismo;


public interface PeopleRepository extends JpaRepository<People, Long> {

	/*      SELECT ph FROM Employee e JOIN e.phones ph WHERE ph LIKE '1%'                   */
	
	
	/*		SELECT u FROM People u WHERE u.turismo.id = :id		*/
	
	
	@Query("SELECT u FROM People u WHERE u.turismo.id = :id")
    List<People> findAllByIdTurismo(Long id);
	
	/*select book from Book book inner join book.chapters chapter where chapter.title like '%hibernate%'*/
	
	

}
