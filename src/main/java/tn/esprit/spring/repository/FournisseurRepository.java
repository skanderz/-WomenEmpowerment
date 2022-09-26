package tn.esprit.spring.repository;



import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Fournisseur;


@Repository 
public interface FournisseurRepository extends CrudRepository<Fournisseur, Long> {
	//@Query("SELECT f FROM Fournisseur f WHERE f.dateNaissance between :d1 and :d2 ")
	//List<Fournisseur> retrieveAllFournisseurs();

}
