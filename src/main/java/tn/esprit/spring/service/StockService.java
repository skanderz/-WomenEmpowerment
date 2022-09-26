package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Stock;
import java.util.Date;

import tn.esprit.spring.entity.searchStock;

public interface StockService {

	List<Stock> retrieveAllStocks();

	Stock addStock(Stock s);

	Stock updateStock(Stock u);

	Stock retrieveStock(Long id);

	void deleteStock(Long id);

	void StockStatut();


	void calculStock(Long idStock);
	List <Stock>searchStcokWithLibelle(String str);
	List<Stock> getStockEnRupture();

	List<Stock> rechercheStcokAvance(searchStock obj);

	List<Stock> getStockLsitOrderByCreatedAtDesc();

	List<Stock> getStockLsitOrderByCreatedAtAsc();

	List<Stock> getStockLsitOrderByUpdatedAtDesc();

	List<Stock> getStockLsitOrderByUpdatedAtAsc();

	List<Stock> getStockLsitOrderByQteDesc();

	List<Stock> getStockLsitOrderByQteAsc();

	List<Stock> getStockLsitOrderByLibelleDesc();

	List<Stock> getStockLsitOrderByLibelleAsc();

	void assignListproductToStock( List<Long> idList ,Long idStock);
	



}
