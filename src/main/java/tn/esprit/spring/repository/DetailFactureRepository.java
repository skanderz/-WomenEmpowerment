package tn.esprit.spring.repository;

import java.util.Date;


import java.util.List;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.DetailFacture;



@Repository
public interface DetailFactureRepository extends CrudRepository<DetailFacture, Long> {


	@Query(value = "Select dF FROM DetailFacture dF WHERE (dF.produit.idProduit= :id_produit) and (dF.facture.dateFacture between :start_date and :end_date)")
	List<DetailFacture> getDetailsFacturebyprod(@Param("id_produit") Long idProduit, @Param("start_date") Date startDate,
			@Param("end_date") Date endDate);
	
/*	@Query("SELECT dF FROM DetailFacture  df WHERE (dF.facture.dateFacture between :start_date and :end_date)")
	List<DetailFacture> getQuantiteByProduit(@Param("category") CategorieProduit category,@Param("prix") float prix, @Param("libelle")String libelle);
*/
	

	@Query(value = "Select SUM(dF.qte * dF.produit.prixUnitaire) FROM DetailFacture dF WHERE (dF.produit.idProduit= :id_produit) and (dF.facture.dateFacture between :start_date and :end_date)")
	float getRevenuBrutProduit(@Param("id_produit") Long idProduit, @Param("start_date") Date startDate,
			@Param("end_date") Date endDate);
	

}
