package tn.esprit.spring.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import tn.esprit.spring.entity.Rayon;

import tn.esprit.spring.entity.SearchRayon;

import tn.esprit.spring.service.RayonService;


@RestController
@Api(tags = "rayon management")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@RequestMapping("/rayon")
public class RayonRestController {

	
	@Autowired
	RayonService rayonService;

	// http://localhost:8089/SpringMVC/rayon/retrieve-all-rayons
	@GetMapping("/retrieve-all-rayons")
	@ApiOperation(value = "Récupérer la liste des rayons")
	@ResponseBody
	public List<Rayon> listRayons() {
		return rayonService.retriveAll();
	}

	// http://localhost:8089/SpringMVC/rayon/add-rayon
	@PostMapping("/add-rayon")
	@ApiOperation(value = "ajouter rayon")
	@ResponseBody
	public Rayon addRayon(@RequestBody Rayon r) {
		Rayon rayon = rayonService.addRayon(r);
		return rayon;
	}

	

	// http://localhost:8089/SpringMVC/rayon/retrieve-rayon/1
	@GetMapping("/retrieve-rayon/{rayon-id}")
	@ApiOperation(value = "Récupérer rayon par id")
	@ResponseBody
	public Rayon retrieveRayon(@PathVariable("rayon-id") Long rayonId) {
		return rayonService.retrieveRayon(rayonId);
	}

	// http://localhost:8089/SpringMVC/rayon/remove-rayon/{rayon-id}
	@DeleteMapping("/remove-rayon/{rayon-id}")
	@ApiOperation(value = "supprimer rayon")
	@ResponseBody
	public void removeRayon(@PathVariable("rayon-id") Long rayonId) {
		rayonService.deleteRayon(rayonId);
	}
	
	@PutMapping("/modify-rayon")
	@ApiOperation(value = "update rayon")
	@ResponseBody
	public Rayon updateRayon(@RequestBody Rayon r) {
	 return	rayonService.updateRayon(r);
	}
	

	@PostMapping(value= {"/search-rayon"})
	@ApiOperation(value = "search multi")
	@ResponseBody
	public List<Rayon> search(@RequestBody SearchRayon obj) {
	
	return rayonService.rechercheRayonAvance(obj);
		
	}
	
	@GetMapping("/rayon-createdAt-Desc")
	@ApiOperation(value = "trie par date creation desc")
	@ResponseBody
	public List<Rayon> getRayonOrderByCreatedAtDesc() { 
		return rayonService.getRayonLsitOrderByCreatedAtDesc();
	}

	@GetMapping("/rayon-createdAt-Asc")
	@ApiOperation(value = "trie par date creation asc")
	@ResponseBody
	public List<Rayon> getRayonOrderByCreatedAtAsc() { 
		return rayonService.getRayonLsitOrderByCreatedAtAsc();
	}
	@GetMapping("/rayon-uapdatedAt-Desc")
	@ApiOperation(value = "trie par date modification desc")
	@ResponseBody
	public List<Rayon> getRayonOrderByUpdatedAtDesc() { 
		return rayonService.getRayonLsitOrderByUpdatedAtDesc();
	}

	@GetMapping("/rayon-uapdatedAt-Asc")
	@ApiOperation(value = "trie par date modification asc")
	@ResponseBody
	public List<Rayon> getRayonOrderByUpdatedAtAsc() { 
		return rayonService.getRayonLsitOrderByUpdatedAtAsc();
	}
	@GetMapping("/rayon-qte-Desc")
	@ApiOperation(value = "trie par qte desc")
	@ResponseBody
	public List<Rayon> getRayonOrderByQteDesc() { 
		return rayonService.getRayonLsitOrderByQteDesc();
	}

	@GetMapping("/rayon-qte-Asc")
	@ApiOperation(value = "trie par qte asc")
	@ResponseBody
	public List<Rayon> getRayonOrderByQteAsc() { 
		return rayonService.getRayonLsitOrderByQteAsc();
	}
	
	@GetMapping("/rayon-libelle-desc")
	@ApiOperation(value = "trie par liebelle desc")
	@ResponseBody
	public List<Rayon> getRayonOrderByLibelleDisc() { 
		return rayonService.getRayonLsitOrderByLibelleDesc();
	}
	
	@GetMapping("/rayon-libelle-asc")
	@ApiOperation(value = "trie par liebelle asc")
	@ResponseBody
	public List<Rayon> getRayonOrderByLibelleAsc() { 
		return rayonService.getRayonLsitOrderByLibelleAsc();
	}
	
	@GetMapping("/rayon-code-desc")
	@ApiOperation(value = "trie par liebelle desc")
	@ResponseBody
	public List<Rayon> getRayonOrderByCodeDisc() { 
		return rayonService.getRayonLsitOrderByCodeDesc();
	}
	
	@GetMapping("/rayon-code-asc")
	@ApiOperation(value = "trie par liebelle asc")
	@ResponseBody
	public List<Rayon> getStockOrderByCodeAsc() { 
		return rayonService.getRayonLsitOrderByCodeAsc();
	}
	
	@PutMapping("/assign-productlist-to-rayon/{rayon-id}")
	@ApiOperation(value = "ajouter une liste des produit pour un rayon")
	@ResponseBody
	public void assignproductListToRayon(@RequestBody List <Long> idList,@PathVariable("rayon-id") Long rayonId) { 
		rayonService.assignListproductToRayon(idList, rayonId);
	}
	
	@PutMapping("/calcul-qte/{rayon-id}")
	@ApiOperation(value = "calcul qte")
	@ResponseBody
	public void calculQte(@PathVariable("rayon-id") Long rayonId) {
		rayonService.calculQte(rayonId);
	}

}
