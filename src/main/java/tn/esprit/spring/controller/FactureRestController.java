package tn.esprit.spring.controller;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.enume.CategorieClient;
import tn.esprit.spring.service.DetailFactureService;
import tn.esprit.spring.service.FactureService;
import tn.esprit.spring.service.ProduitService;

@RestController
@Api(tags = "invoice management")
@RequestMapping("/facture")
public class FactureRestController {

	@Autowired
	FactureService factureService;

	@Autowired
	ProduitService produitService;

	@Autowired
	DetailFactureService detailFactureService;

	@GetMapping("/retrieve-all-invocies")
	@ApiOperation(value = "Récupérer la liste des factures")
	@ResponseBody
	public List<Facture> listFactures() {
		return factureService.retrieveAllFactures();
	}

	@GetMapping("/cancel-invoce/{invoice-id}")
	@ApiOperation(value = "cancel facture")
	@ResponseBody
	public void cancelInvoice(@PathVariable("invoice-id") Long invoiceId) {
		factureService.cancelFacture(invoiceId);
	}

	@GetMapping("/retrive-invoce/{invoice-id}")
	@ApiOperation(value = "get invoice by id")
	@ResponseBody
	public Facture retrieveFacture(@PathVariable("invoice-id") Long invoiceId) {
		return factureService.retrieveFacture(invoiceId);
	}

	@GetMapping("/retrive-invoice-by-client-id/{client-id}")
	@ApiOperation("get invoice by client id")
	@ResponseBody
	public List<Facture> getInvoiceByClientId(@PathVariable("client-id") Long id) {

		return this.factureService.getFactureByClient(id);
	}

	@PostMapping("/add-invoice/{client-id}")
	@ApiOperation("add invoice")
	@ResponseBody
	Facture addFactureRest(@RequestBody Facture f, @PathVariable("client-id") Long idClient) {
		return factureService.addFacture(f, idClient);
	}

	@GetMapping("/get-ca/{cat}/{start-date}/{end-date}")
	@ApiOperation("get ca par categorie client")
	@ResponseBody
	float getChiffreAffaireParCategorieClient(@PathVariable("cat") CategorieClient categorieClient,
			@PathVariable("start-date") String  startDate,@PathVariable("end-date") String endDate) throws ParseException {
		return factureService.getChiffreAffaireParCategorieClient(categorieClient, new SimpleDateFormat("dd-MM-yyyy").parse(startDate), new SimpleDateFormat("dd-MM-yyyy").parse(endDate));
	}
	
	
}
