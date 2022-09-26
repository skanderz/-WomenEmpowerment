package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.StockRepository;
import tn.esprit.spring.service.StockServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceImplTest {
	@Autowired
	StockServiceImpl stockServiceImpl;

	@Autowired
	StockRepository stockRepository;

	@Test
	public void testAddStock() {
		try {
//			List<Stock> stocks = stockServiceImpl.retrieveAllStocks();
//			int expected = stocks.size();
//			System.out.println("expected : " + expected);
			Stock s = new Stock();
			s.setLibelleStock("stock test");
			s.setQte(10);
			s.setQteMin(100);
		//	System.out.println("stock : " + s);
			Stock savedStock = stockServiceImpl.addStock(s);
//			assertEquals(expected + 1, stockServiceImpl.retrieveAllStocks().size());
//			System.out.println("new size : " + stockServiceImpl.retrieveAllStocks().size());
//			assertNotNull(savedStock.getLibelleStock());

//			System.out.println("------------------------------------------------");
//			Stock stock = stockRepository.findByLibelleStock("stock test");
//			assertThat(stock.getLibelleStock()).isEqualTo("stock test");
//			System.out.println("------------------------------------------------");
//			assertNotNull(savedStock);
			assertThat(savedStock.getIdStock()).isNotNull();
//			assertEquals(savedStock.getLibelleStock(), "stock test");
//		    assertNotNull(savedStock.getIdStock());
			stockServiceImpl.deleteStock(savedStock.getIdStock());
			Stock stock = stockServiceImpl.retrieveStock(savedStock.getIdStock());
			assertNull(stock);

		} catch (Exception e) {
			throw e;
		}
	}
}
