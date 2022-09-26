package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Entrepot;
import tn.esprit.spring.entity.Fournisseur;


public interface IFournisseurService {
	
    List<Fournisseur> retrieveAllFournisseurs();
	
    Fournisseur addFournisseur(Fournisseur f);

	void deleteFournisseur(Long id);

	Fournisseur updateFournisseur(Fournisseur f);

	Fournisseur retrieveFournisseur(Long id);
	
	 List<Entrepot> getEntrepotsFournisseurs(Fournisseur f);

}
