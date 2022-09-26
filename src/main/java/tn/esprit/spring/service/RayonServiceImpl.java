package tn.esprit.spring.service;

import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.Rayon;
import tn.esprit.spring.entity.SearchRayon;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.RayonRepository;


@Service
public class RayonServiceImpl implements RayonService {

	@Autowired
	RayonRepository rayonRepository;
	@Autowired
	ProduitRepository produitRepository;


	@Override
	public Rayon addRayon(Rayon r) {
		// TODO Auto-generated method stub
		r.setCreatedAt(new Date());
		return rayonRepository.save(r);
	}

	@Override
	public Rayon retrieveRayon(Long id) {
		// TODO Auto-generated method stub
		return rayonRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteRayon(Long id) {
		rayonRepository.deleteById(id);

	}

	@Override
	public List<Rayon> retriveAll() {

	return (List<Rayon>) rayonRepository.findAll();
		
	}

	

	@Override
	public Rayon updateRayon(Rayon r) {
		// TODO Auto-generated method stub
		r.setUpdatedAt(new Date());
		return rayonRepository.save(r);
	}


	@Override
	public void calculQte(Long idRayon) {
		Rayon r = retrieveRayon(idRayon);
		r.setQte(produitRepository.calculQteRayon(idRayon));
		updateRayon(r);

	}

	@Override
	public List<Rayon> rechercheRayonAvance(SearchRayon obj) {
		// TODO Auto-generated method stub
		return rayonRepository.rechercheRayonAvance(obj.getQuery(), obj.getDateDebut(), obj.getDateFin(),
				obj.getNbrProduct());
	}

	@Override
	public List<Rayon> getRayonLsitOrderByCreatedAtDesc() {
		// TODO Auto-generated method stub
		return rayonRepository.findAllByOrderByCreatedAtDesc();
	}

	@Override
	public List<Rayon> getRayonLsitOrderByCreatedAtAsc() {
		// TODO Auto-generated method stub
		return rayonRepository.findAllByOrderByCreatedAtAsc();
	}

	@Override
	public List<Rayon> getRayonLsitOrderByUpdatedAtDesc() {
		// TODO Auto-generated method stub
		return rayonRepository.findAllByOrderByUpdatedAtDesc();
	}

	@Override
	public List<Rayon> getRayonLsitOrderByUpdatedAtAsc() {
		// TODO Auto-generated method stub
		return rayonRepository.findAllByOrderByUpdatedAtAsc();

	}

	@Override
	public List<Rayon> getRayonLsitOrderByQteDesc() {
		// TODO Auto-generated method stub
		return rayonRepository.findAllByOrderByQteDesc();
	}

	@Override
	public List<Rayon> getRayonLsitOrderByQteAsc() {
		// TODO Auto-generated method stub
		return rayonRepository.findAllByOrderByQteAsc();
	}

	@Override
	public List<Rayon> getRayonLsitOrderByLibelleDesc() {
		List<Rayon> sortedList = retriveAll().stream().sorted(((x1, x2) -> x2.getLibelle().compareTo(x1.getLibelle())))
				.collect(Collectors.toList());
		return sortedList;
	}

	@Override
	public List<Rayon> getRayonLsitOrderByLibelleAsc() {
		List<Rayon> sortedList = retriveAll().stream().sorted(((x1, x2) -> x1.getLibelle().compareTo(x2.getLibelle())))
				.collect(Collectors.toList());
		return sortedList;
	}

	@Override
	public List<Rayon> getRayonLsitOrderByCodeDesc() {
		List<Rayon> sortedList = retriveAll().stream().sorted(((x1, x2) -> x2.getCode().compareTo(x1.getCode())))
				.collect(Collectors.toList());
		return sortedList;
	}

	@Override
	public List<Rayon> getRayonLsitOrderByCodeAsc() {
		List<Rayon> sortedList = retriveAll().stream().sorted(((x1, x2) -> x1.getCode().compareTo(x2.getCode())))
				.collect(Collectors.toList());
		return sortedList;
	}

	@Override
	public void assignListproductToRayon(List<Long> idList, Long idRayon) {
		// TODO Auto-generated method stub
		List<Produit> productList = produitRepository.getProductListByIds(idList);
		Rayon myRayon = retrieveRayon(idRayon);
		for (Produit p : productList) {
			p.setRayon(myRayon);
			produitRepository.save(p);
		}

	}


}
