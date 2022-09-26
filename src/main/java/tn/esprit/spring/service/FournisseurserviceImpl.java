package tn.esprit.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Entrepot;
import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.repository.EntrepotRepository;
import tn.esprit.spring.repository.FournisseurRepository;

@Service
public class FournisseurserviceImpl implements FournisseurService {

	@Autowired
	EntrepotRepository entrepotrepository;

	@Autowired
	FournisseurRepository fournisseurRepo;
	@Autowired
	FournisseurRepository fournisseurrepository;

	@Transactional
	public Fournisseur addFournisseur(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		return this.fournisseurRepo.save(fournisseur);
	}

	@Override
	public Fournisseur retriveFournisseur(Long id) {
		// TODO Auto-generated method stub
		return this.fournisseurRepo.findById(id).orElse(null);
	}

	@Override
	public Fournisseur retrieveFournisseur(Long id) {
		// TODO Auto-generated method stub
		return this.fournisseurRepo.findById(id).orElse(null);
	}

	@Override
	public List<Fournisseur> retrieveAllFournisseurs() {
		// TODO Auto-generated method stub
		return (List<Fournisseur>) fournisseurrepository.findAll();
	}

	@Override
	public void deleteFournisseur(Long id) {
		// TODO Auto-generated method stub
		fournisseurrepository.deleteById(id);
	}

	@Override
	public Fournisseur updateFournisseur(Fournisseur f) {
		// TODO Auto-generated method stub
		return fournisseurrepository.save(f);

	}

	@Override
	public List<Entrepot> getEntrepotsFournisseurs(Fournisseur f) {
		// TODO Auto-generated method stub
		return entrepotrepository.getentrepotparfournisseur(f);

	}

}
