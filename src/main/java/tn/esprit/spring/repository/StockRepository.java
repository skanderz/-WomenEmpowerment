package tn.esprit.spring.repository;


import java.util.Date;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entity.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {

	Stock findByLibelleStock(String s);

	
	

	@Query("SELECT s FROM Stock s WHERE s.qteMin >= s.qte")
	List<Stock> retrieveStock();

	@Query("SELECT s FROM Stock s WHERE (s.qteMin >= s.qte) AND (s.checked = FALSE)")
	List<Stock> retrieveStockEnRp();
	
	@Query("SELECT s FROM Stock s WHERE (:str is null or s.libelleStock LIKE %:str% ) and  ((:date1 is null or s.createdAt =:date1) "
			+ "or ( s.createdAt BETWEEN :date1 and :date2)) and(:nbr is null or s.qte >=:nbr)")
	List<Stock> rechercheStcokAvance(@Param("str") String str , @Param("date1") Date d1, @Param("date2") Date d2, @Param("nbr") int nbr);
	
	
	
	List<Stock> findAllByOrderByCreatedAtAsc();
	List<Stock> findAllByOrderByCreatedAtDesc();
	List<Stock> findAllByOrderByUpdatedAtAsc();
	List<Stock> findAllByOrderByUpdatedAtDesc();
	List<Stock> findAllByOrderByQteDesc();
	List<Stock> findAllByOrderByQteAsc();
	
	


}
