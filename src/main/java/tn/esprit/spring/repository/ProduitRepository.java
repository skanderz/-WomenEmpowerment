package tn.esprit.spring.repository;





import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.enume.CategorieProduit;
@Repository
public interface ProduitRepository extends CrudRepository<Produit, Long> {
	
//	@Query("SELECT  p FROM Produit p WHERE p.idProduit in :idPs")
//	List<Produit> retrieveProduitsByIdS(@Param("idPs") List<Long>
//	ids);
	
	@Query("SELECT  p.prixUnitaire FROM Produit p WHERE p.idProduit = :idP")
	float retrievePrixUnitaitreById(@Param("idP") Long id);
	
	@Query("SELECT  p FROM Produit p WHERE p.libelle LIKE %:libelle%")
	List<Produit> getProduitBylibelle(@Param("libelle") String libelle);
	
	@Query("SELECT  p FROM Produit p WHERE p.detailProduit.categorieProduit LIKE %:category%")
	List<Produit> getProduitBycategory(@Param("category") CategorieProduit category);
	
	@Query("SELECT p FROM Produit p WHERE (:str is null or p.libelle LIKE %:str% ) and (:category is null or p.detailProduit.categorieProduit=:category) and ((:date1 is null or p.detailProduit.dateCreation =:date1) "
			+ "or ( p.detailProduit.dateCreation BETWEEN :date1 and :date2)) and(p.prixUnitaire <= :prixUintaire)")
	List<Produit> rechercheProduitAvance(@Param("str") String str , @Param("date1") Date d1,@Param("date2") Date d2,@Param("prixUintaire") float prixUintaire,@Param("category") CategorieProduit category);
	
	/*
	@Query("SELECT  p FROM Produit p WHERE p.detailProduit = :category")
	List<Produit> getProduitByManycategory(@Param("category1") CategorieProduit category);
	*/
	@Query("SELECT  MIN(p) FROM Produit p ")
	Produit getMin();
	
	@Query("SELECT  MAX(p) FROM Produit p ")
	Produit getMax();
	
	@Query("SELECT  p FROM Produit p WHERE p.prixUnitaire<= :prixUnitaire1 ")
	List<Produit> getProduitByprixbetween(@Param("prixUnitaire1") float prixUnitaire1);
	
	@Query("SELECT  p FROM Produit p WHERE p.prixUnitaire<= :prixUnitaire1 and  p.detailProduit.categorieProduit = :category and p.libelle = :libelle")
	List<Produit> getByFiltre(@Param("category") CategorieProduit category,@Param("prix") float prix, @Param("libelle")String libelle);
	

//	@Query("SELECT  p FROM Produit p WHERE p.idProduit in :idPs")
//	List<Produit> retrieveProduitsByIdS(@Param("idPs") List<Long>
//	ids);





	

	/*
	 * @Query("SELECT  p FROM Produit p WHERE p.detailProduit = :category")
	 * List<Produit> getProduitByManycategory(@Param("category1") CategorieProduit
	 * category);
	 */



	

	@Query("SELECT  count(*) FROM Produit p WHERE p.stock.idStock = :idS")
	int calculStock(@Param("idS") Long id);
	
	@Query("SELECT  count(*) FROM Produit p WHERE p.rayon.idRayon = :idR")
	int calculQteRayon(@Param("idR") Long id);
	
	@Query("SELECT  count(*) FROM Produit p WHERE p.stock.idStock = :idS and p.detailProduit.categorieProduit =:cat")
	int getNbProductByStockAndCat(@Param("idS") Long id ,@Param("cat") String cat);

	@Query("SELECT  p FROM Produit p WHERE p.idProduit IN :idPs")
	List<Produit> getProductListByIds(@Param("idPs") List<Long> idPs);

	@Query("SELECT  p FROM Produit p WHERE p.stock.idStock <> :idS")
	List<Produit> reteiveProductsNotAvInStcok(@Param("idS") Long idS);
	
	@Query("SELECT  p FROM Produit p WHERE p.rayon.idRayon <> :idR")
	List<Produit> reteiveProductsNotAvInRayon(@Param("idR") Long idR);
}
