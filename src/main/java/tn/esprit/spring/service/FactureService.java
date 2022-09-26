package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.enume.CategorieClient;

public interface FactureService {
	List<Facture> retrieveAllFactures();

	void cancelFacture(Long id);

	Facture retrieveFacture(Long id);

	void retrieveCA();

	List<Facture> getFactureByClient(Long id);
	
	Facture addFacture(Facture f, Long idClient);
	
	
	float getChiffreAffaireParCategorieClient(CategorieClient categorieClient,
			Date startDate, Date endDate);
}
