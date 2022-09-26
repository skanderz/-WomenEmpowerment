package tn.esprit.spring.service;


import java.util.Date;

import java.util.List;



import java.util.stream.Collectors;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import tn.esprit.spring.entity.Stock;

import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.searchStock;

import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.StockRepository;

@Service
@Slf4j
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	ProduitRepository produitRepository;


	@Autowired
	ProduitService produitService;


	@Override
	public List<Stock> retrieveAllStocks() {
		// TODO Auto-generated method stub
		return (List<Stock>) stockRepository.findAll();
	}

	@Override
	public Stock addStock(Stock s) {
		// TODO Auto-generated method stub
		s.setCreatedAt(new Date());
		return stockRepository.save(s);
	}

	@Override
	public Stock updateStock(Stock s) {
		// TODO Auto-generated method stub

		s.setUpdatedAt(new Date());
		return stockRepository.save(s);
	}

	@Override
	public Stock retrieveStock(Long id) {
		// TODO Auto-generated method stub
		return stockRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteStock(Long id) {
		stockRepository.deleteById(id);

	}

	@Override
	@Scheduled(cron = "*/60 * * * * *")
	public void StockStatut() {
		// TODO Auto-generated method stub
		List<Stock> stockList;
		stockList = (List<Stock>) stockRepository.retrieveStock();
		for (Stock item : stockList) {
			log.info(item.getLibelleStock() + " en rupture la quantité min est " + item.getQteMin()
					+ " la quant actuelle est " + item.getQte());
		}
	}

	@Transactional
	public void calculStock(Long idStock) {
		Stock s = retrieveStock(idStock);
		s.setQte(produitRepository.calculStock(idStock));
		updateStock(s);
	}

	@Override
	public List<Stock> getStockEnRupture() {
		// TODO Auto-generated method stub
		return stockRepository.retrieveStockEnRp();

	}

	


	

	@Override
	public List<Stock> rechercheStcokAvance(searchStock obj) {
		// TODO Auto-generated method stub

		return stockRepository.rechercheStcokAvance(obj.getQuery(), obj.getDateDebut(), obj.getDateFin(),
				obj.getNbrProduct());
	}

	@Override
	public List<Stock> getStockLsitOrderByCreatedAtDesc() {
		// TODO Auto-generated method stub
		return stockRepository.findAllByOrderByCreatedAtDesc();
	}

	@Override
	public List<Stock> getStockLsitOrderByCreatedAtAsc() {
		// TODO Auto-generated method stub
		return stockRepository.findAllByOrderByCreatedAtAsc();
	}

	@Override
	public List<Stock> getStockLsitOrderByUpdatedAtDesc() {
		// TODO Auto-generated method stub
		return stockRepository.findAllByOrderByUpdatedAtDesc();
	}

	@Override
	public List<Stock> getStockLsitOrderByUpdatedAtAsc() {
		// TODO Auto-generated method stub
		return stockRepository.findAllByOrderByUpdatedAtAsc();
	}

	@Override
	public List<Stock> getStockLsitOrderByQteDesc() {
		// TODO Auto-generated method stub
		return stockRepository.findAllByOrderByQteDesc();
	}

	@Override
	public List<Stock> getStockLsitOrderByQteAsc() {
		// TODO Auto-generated method stub
		return stockRepository.findAllByOrderByQteAsc();
	}

	@Override
	public List<Stock> getStockLsitOrderByLibelleDesc() {
		// TODO Auto-generated method stub
		List<Stock> sortedList = retrieveAllStocks().stream()
				.sorted(((x1, x2) -> x2.getLibelleStock().compareTo(x1.getLibelleStock())))
				.collect(Collectors.toList());
		return sortedList;
	}

	@Override
	public List<Stock> getStockLsitOrderByLibelleAsc() {
		// TODO Auto-generated method stub

		List<Stock> sortedList = retrieveAllStocks().stream()
				.sorted(((x1, x2) -> x1.getLibelleStock().compareTo(x2.getLibelleStock())))
				.collect(Collectors.toList());
		return sortedList;
	}

	@Override
	public void assignListproductToStock(List<Long> idList,Long idStock ) {
		// TODO Auto-generated method stub
		List<Produit> productList = produitRepository.getProductListByIds(idList);
		Stock myStock = retrieveStock(idStock);
		for (Produit p : productList) {
			p.setStock(myStock);
			produitRepository.save(p);
		}

	}

	@Override
	public List<Stock> searchStcokWithLibelle(String str) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
