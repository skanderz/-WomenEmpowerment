package tn.esprit.spring.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Rayon;



@Repository
public interface RayonRepository extends CrudRepository<Rayon, Long> {
	@Query("SELECT r FROM Rayon r WHERE (:str is null or r.libelle LIKE %:str%  or  r.code LIKE %:str%) and  ((:date1 is null or r.createdAt =:date1) "
			+ "or ( r.createdAt BETWEEN :date1 and :date2)) and(:nbr is null or r.qte >=:nbr)")
	List<Rayon> rechercheRayonAvance(@Param("str") String str , @Param("date1") Date d1, @Param("date2") Date  d2, @Param("nbr") int nbr);
	
	List<Rayon> findAllByOrderByCreatedAtAsc();
	List<Rayon> findAllByOrderByCreatedAtDesc();
	List<Rayon> findAllByOrderByUpdatedAtAsc();
	List<Rayon> findAllByOrderByUpdatedAtDesc();
	List<Rayon> findAllByOrderByQteDesc();
	List<Rayon> findAllByOrderByQteAsc();
	

}
