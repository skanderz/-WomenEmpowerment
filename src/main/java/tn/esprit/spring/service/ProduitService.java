package tn.esprit.spring.service;

import java.text.ParseException;

import java.util.List;

import tn.esprit.spring.entity.Produit;

import tn.esprit.spring.entity.SearchProduit;

import tn.esprit.spring.enume.CategorieProduit;

public interface ProduitService {
	List<Produit> retrieveAllProduits();

	Produit addProduit(Produit p, Long idRayon, Long idStock);

	Produit retrieveProduit(Long id);

	Produit assignProduitToStock(Long idProduit, Long idStock);

	Produit updateProduit(Produit p, Long idProduit);

	void assignFournisseurToProduit(Long fournisseurId, Long produitId);

	float getPrixUnitaitreById(Long id);

	public Produit AssignImageToproduct(Long idImage, Long idProduit);

	void deleteProduit(Long id);

	List<Produit> getProduitByLibelle(String libelle);

	List<Produit> getProduitBycategory(CategorieProduit category);

	List<Produit> getProduitByprixbetween(float prixUnitaire1, float prixUnitaire2);

	List<Produit> getProduitByprixbetween(float prixUnitaire1);

	Produit getMax();

	Produit getMin();

	List<Produit> getProduitByFiltre(CategorieProduit category, float prix, String libelle);

	Produit assignProduitToRayon(Long idProduit, Long idRayon);

	Produit getProduitPlusVendu(String startDate, String endDate) throws ParseException;

	List<Long> GetIdProduit();

	List<Produit> rechercheProduitAvance(SearchProduit obj);

	List<Produit> getProductListNotAvInRayon(Long stockId);

	int getNbProductByStockAndCat(Long idStock, String cat);

	List<Produit> getProductListNotAvInStock(Long stockId);

}
