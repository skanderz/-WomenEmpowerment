package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.enume.CategorieClient;
import tn.esprit.spring.repository.FactureRepository;

@Service
@Slf4j
public class FactureServiceImpl implements FactureService {

	@Autowired
	FactureRepository factureRepository;

	@Autowired
	UserService clientService;

	@Autowired
	ProduitService produitService;
	
	@Autowired
	DetailFactureService detailFactureService;

	@Override
	public List<Facture> retrieveAllFactures() {

		return (List<Facture>) factureRepository.findAll();
	}

	@Override
	public void cancelFacture(Long id) {
		Facture facture = this.retrieveFacture(id);
		facture.setActive(false);
		this.factureRepository.save(facture);
	}

	@Override
	public Facture retrieveFacture(Long id) {
		return this.factureRepository.findById(id).orElse(null);
	}

	@Override
	@Scheduled(cron = "1 * * 1 JAN *")
	public void retrieveCA() {
		this.factureRepository.retrieveCA();

		float ca = this.factureRepository.retrieveCA();
		;

		log.info(new Date() + "Chiffre d'affaire : " + ca);

	}

	@Override
	public List<Facture> getFactureByClient(Long id) {
		// TODO Auto-generated method stub
		return factureRepository.getFactureByClient(id);
	}

	@Override
	public Facture addFacture(Facture f, Long idClient) {
		List<DetailFacture> myList = new ArrayList<DetailFacture>();
		float montantFacture = 0;
		float montantRemiseFacture = 0;
		for (DetailFacture d : f.getDetailFactureList()) {
			float p = this.produitService.getPrixUnitaitreById(d.getProduit().getIdProduit());
			d.getProduit().setPrixUnitaire(p);
			float prixUnitaire = d.getProduit().getPrixUnitaire();
			float prixAvantRemise = prixUnitaire * d.getQte();
			if (d.getPourcentageRemise() != 0) {
				d.setMontantRemise(d.getPourcentageRemise() * prixAvantRemise / 100);
				d.setPrixTotal(prixAvantRemise - d.getMontantRemise());
			} else {
				d.setPrixTotal(prixAvantRemise);
			}
			montantFacture=montantFacture+d.getPrixTotal();
			montantRemiseFacture=montantRemiseFacture+d.getMontantRemise();
			myList.add(d);
		}
		f.setDateFacture(new Date());
		f.setMontantFacture(montantFacture);
		f.setMontantRemise(montantRemiseFacture);
		User c =clientService.retrieveUser(idClient);
		f.setClient(c);
		Facture fc=factureRepository.save(f);
		for(DetailFacture dF:myList) {
			dF.setFacture(f);
			detailFactureService.addDetailFacture(dF);
		}
		return fc;
	}

	@Override
	public float getChiffreAffaireParCategorieClient(CategorieClient categorieClient, Date startDate, Date endDate) {
		
		return factureRepository.getChiffreAffaireParCategorieClient(categorieClient, startDate, endDate);
	}

}
