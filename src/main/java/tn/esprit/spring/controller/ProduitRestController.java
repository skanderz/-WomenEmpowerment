package tn.esprit.spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entity.Produit;

import tn.esprit.spring.entity.SearchProduit;

import tn.esprit.spring.enume.CategorieProduit;
import tn.esprit.spring.service.ProduitService;

@RestController
@Api(tags = "product management")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@RequestMapping("/produit")
public class ProduitRestController {

	@Autowired
	ProduitService produitService;

	@ApiOperation(value = "ajouter produit")
	// http://localhost:8089/SpringMVC/produit/add-produit/3/1
	@PostMapping("/add-produit/{stock-id}/{rayon-id}")
	@ResponseBody
	public Produit addProduit(@RequestBody Produit p, @PathVariable("stock-id") Long sotckId,
			@PathVariable("rayon-id") Long rayonId) {

		return produitService.addProduit(p, sotckId, rayonId);
	}

	// http://localhost:8089/SpringMVC/produit/retrieve-all-produits
	@GetMapping("/retrieve-all-produits")
	@ApiOperation(value = "Récupérer la liste des produit")
	@ResponseBody
	public List<Produit> listProduits() {
		return produitService.retrieveAllProduits();
	}

	
	


	@PutMapping("/modify-produit/{produit-id}")
	@ApiOperation(value = "Modifier la liste des produit")
	@ResponseBody
	public Produit UpdateProduit(@RequestBody Produit p, @PathVariable("produit-id") Long produitId) {
		return produitService.updateProduit(p, produitId);

	}

	// http://localhost:8089/SpringMVC/produit/retrieve-produit/1
	@GetMapping("/retrieve-produit/{produit-id}")
	@ApiOperation(value = "Récupérer produit par id")
	@ResponseBody
	public Produit retrieveProduit(@PathVariable("produit-id") Long produitId) {
		return produitService.retrieveProduit(produitId);
	}

	@PutMapping("/assignProduitToStock/{produit-id}/{stock-id}")
	@ApiOperation(value = "assign Produit To Stock")
	@ResponseBody
	public Produit assignProduitToStock(@PathVariable("produit-id") Long produitId,
			@PathVariable("stock-id") Long stockId) {
		return produitService.assignProduitToStock(produitId, stockId);
	}

	
	@PutMapping("/assignProduitToRayon/{produit-id}/{rayon-id}")
	@ApiOperation(value = "assign Produit To Stock")
	@ResponseBody
	public Produit assignProduitToRayon(@PathVariable("produit-id") Long produitId,
			@PathVariable("rayon-id") Long rayonId) {
		return produitService.assignProduitToRayon(produitId, rayonId);
	}
	
	@PutMapping("/assignProduitToImage/{id}/{produit-id}")
	@ApiOperation(value = "assign Produit To image")
	@ResponseBody
	public Produit AssignImageToproduct(@PathVariable("id") Long ImageId,@PathVariable("produit-id") Long produitId) {
		return produitService.AssignImageToproduct(ImageId,produitId);
	}


	@ApiOperation("assing provider to product")
	@PutMapping("/assing-provider-to-product/{provider-id}/{product-id}")
	public void assignProviderToProduct(@PathVariable("provider-id") Long providerId,
			@PathVariable("product-id") Long productId) {
		this.produitService.assignFournisseurToProduit(providerId, productId);
	}


	@DeleteMapping("/remove-produit/{produit-id}")
	@ResponseBody
	public void removeProduit(@PathVariable("produit-id") Long produitId) {
		produitService.deleteProduit(produitId);
	}

	
	
	
	@GetMapping("/retrieve-all-produits3/{libelle}")
	@ResponseBody
	public List<Produit> getProduitsByLibelle(@PathVariable("libelle") String libelle) {
		
		return produitService.getProduitByLibelle(libelle);
	}
	
	
	@GetMapping("/retrieve-all-produits1/{category}")
	@ResponseBody
	public List<Produit> getProduitBycategory(@PathVariable("category") CategorieProduit category) {
		
		return produitService.getProduitBycategory(category);
	}
	
	

	
	@GetMapping("/retrieve-all-produits2/{prix}")
	@ResponseBody
	public List<Produit> getProduitByprixbetween(@PathVariable("prix") float prix) {
		
		return produitService.getProduitByprixbetween(prix);
	}
	
	@GetMapping("/get-Max")
	@ResponseBody
	public Produit getMax() {
		
		return produitService.getMax();
	}
	
	
	@GetMapping("/get-Min")
	@ResponseBody
	public Produit getMin() {
		
		return produitService.getMin();
	}
	
	
	@GetMapping("/get-produit-plus-vendu/{start-date}/{end-date}")
	@ApiOperation("get quantite")
	@ResponseBody
	public Produit getProduitPlusVendu(@PathVariable("start-date") String startDate,@PathVariable("end-date") String endDate) throws ParseException {
		return produitService.getProduitPlusVendu(startDate,endDate);
		
		
	}
	
	
	@GetMapping("/get-idProduits")
	@ResponseBody
	public List<Long> GetIdProduit() {
		
		List<Long> a =produitService.GetIdProduit();
		System.out.print(a);
		return produitService.GetIdProduit();
	}
	
	@PostMapping(value= {"/search-produit"})
	@ApiOperation(value = "search multi criteres")
	@ResponseBody
	public List<Produit> search(@RequestBody SearchProduit obj){
	
		System.out.print(obj.getQuery());
		System.out.print(obj.getDateDebut());
		System.out.print(obj.getDateFin());
	return produitService.rechercheProduitAvance(obj);
		
	}
	
	@GetMapping("/retrieve-notstock-produits/{stock-id}")
	@ApiOperation(value = "Récupérer la liste des produit qui n'existe pas dans un stock ")
	@ResponseBody
	public List<Produit> retriveProductListNotAvInStock(@PathVariable("stock-id") Long stockId) {
		return produitService.getProductListNotAvInStock(stockId);
	}
	
	@GetMapping("/retrieve-notrayon-produits/{stock-id}")
	@ApiOperation(value = "Récupérer la liste des produit qui n'existe pas dans un stock ")
	@ResponseBody
	public List<Produit> retriveProductListNotAvInRayon(@PathVariable("stock-id") Long stockId) {
		return produitService.getProductListNotAvInRayon(stockId);
	}
	
	//stat jasser
	
	@GetMapping("/retrieve-nb-produit/{stock-id}/{cat}")
	@ApiOperation(value = "Récupérer le nombre des produit par stock et categorie")
	@ResponseBody
	public int getNbProductByStockAndCat(@PathVariable("stock-id") Long stockId,@PathVariable("cat") String cat) {
		return produitService.getNbProductByStockAndCat(stockId,cat);
	}


}
