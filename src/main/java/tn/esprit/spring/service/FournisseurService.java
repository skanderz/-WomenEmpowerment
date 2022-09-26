package tn.esprit.spring.service;


import java.util.List;

import tn.esprit.spring.entity.Entrepot;

//import tn.esprit.spring.entity.Entrepot;

import tn.esprit.spring.entity.Fournisseur;

public interface FournisseurService {

	Fournisseur addFournisseur(Fournisseur fournisseur);

	Fournisseur retriveFournisseur(Long id);

	Fournisseur retrieveFournisseur(Long id);

List<Fournisseur> retrieveAllFournisseurs();
	
	void deleteFournisseur(Long id);

	Fournisseur updateFournisseur(Fournisseur f);

	
 List<Entrepot> getEntrepotsFournisseurs(Fournisseur f);


}
