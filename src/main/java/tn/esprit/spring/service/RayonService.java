package tn.esprit.spring.service;

import java.util.List;



import tn.esprit.spring.entity.Rayon;

import tn.esprit.spring.entity.SearchRayon;
import tn.esprit.spring.entity.Stock;



public interface RayonService {

	Rayon addRayon(Rayon r);

	List <Rayon> retriveAll();
	void deleteRayon(Long id);

	Rayon retrieveRayon(Long id);
	
	Rayon updateRayon(Rayon r);

	
	void calculQte(Long idRayon);
	
	List<Rayon> rechercheRayonAvance(SearchRayon obj);
	
	List<Rayon> getRayonLsitOrderByCreatedAtDesc();

	List<Rayon> getRayonLsitOrderByCreatedAtAsc();

	List<Rayon> getRayonLsitOrderByUpdatedAtDesc();

	List<Rayon> getRayonLsitOrderByUpdatedAtAsc();

	List<Rayon> getRayonLsitOrderByQteDesc();

	List<Rayon> getRayonLsitOrderByQteAsc();

	List<Rayon> getRayonLsitOrderByLibelleDesc();

	List<Rayon> getRayonLsitOrderByLibelleAsc();
	
	List<Rayon> getRayonLsitOrderByCodeDesc();

	List<Rayon> getRayonLsitOrderByCodeAsc();
	
	void assignListproductToRayon(List<Long> idList,Long idRayon );
}
