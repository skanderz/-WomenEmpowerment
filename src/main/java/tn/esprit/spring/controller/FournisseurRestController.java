package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.service.FournisseurService;

@RestController
@Api(tags = "provider managment")
@RequestMapping("/provider")
public class FournisseurRestController {

	@Autowired
	FournisseurService fournisseurService;
	@Autowired
	FournisseurService fournisseurservice;

	// http://localhost:8089/SpringMVC/Fournisseur/retrieve-all-fournisseurs
	@GetMapping("/retrieve-all-fournisseurs")
	@ResponseBody
	public List<Fournisseur> getFournisseurs() {
		List<Fournisseur> listfournisseurs = fournisseurService.retrieveAllFournisseurs();
		return listfournisseurs;

	}

	// http://localhost:8089/SpringMVC/fournisseur/retrieve-fournisseur/8
	@GetMapping("/retrieve-fournisseur/{fournisseur-id}")
	@ResponseBody
	public Fournisseur retrieveFournisseur(@PathVariable("fournisseur-id") Long FournisseurId) {
		return fournisseurservice.retrieveFournisseur(FournisseurId);
	}

	// http://localhost:8089/SpringMVC/fournisseur/add-fournisseur
	@PostMapping("/add-fournisseur")
	@ResponseBody
	public Fournisseur addFournisseur(@RequestBody Fournisseur f) {
		Fournisseur fournisseur = fournisseurservice.addFournisseur(f);
		return fournisseur;
	}

	// http://localhost:8089/SpringMVC/fournisseur/remove-fournisseur/{fournisseur-id}
	@DeleteMapping("/remove-fournisseur/{fournisseur-id}")
	@ResponseBody
	public void removeFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
		fournisseurservice.deleteFournisseur(fournisseurId);
	}

	// http://localhost:8089/SpringMVC/fournisseur/modify-fournisseur
	@PutMapping("/modify-fournisseur")
	@ResponseBody
	public Fournisseur modifyFournisseur(@RequestBody Fournisseur fournisseur) {
		return fournisseurservice.updateFournisseur(fournisseur);
	}

	@ApiOperation("add provider")
	@PostMapping("/add-provider")
	@ResponseBody
	public Fournisseur addProvider(@RequestBody Fournisseur f) {

		return this.fournisseurService.addFournisseur(f);
	}

	@ApiOperation("get provider by id")
	@GetMapping("retrive-provider/{provider-id}")
	@ResponseBody
	public Fournisseur getProviderById(@PathVariable("provider-id") Long providerId) {

		return fournisseurService.retriveFournisseur(providerId);
	}
}
