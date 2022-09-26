package tn.esprit.spring.controller;

import java.text.ParseException;

import java.text.SimpleDateFormat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import tn.esprit.spring.service.DetailFactureService;

@RestController
@Api(tags = "detail invoice management")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })

@RequestMapping("/detail-invoce")
public class DetailFactureRestController {
	
	@Autowired
	DetailFactureService detailFactureService;
	

	@GetMapping("/get-revenue-brute/{id-produit}/{start-date}/{end-date}")
	@ApiOperation("get revenue brute")
	@ResponseBody
	double getRevenuBrutProduit(@PathVariable("id-produit") Long idProduit,
			@PathVariable("start-date") String  startDate,@PathVariable("end-date") String endDate) throws ParseException {
		
		System.out.println("rest");
		System.out.println(idProduit);
		System.out.println(endDate);
		System.out.println(startDate);
		return detailFactureService.getRevenuBrutProduit(idProduit, new SimpleDateFormat("yyyy-MM-dd").parse(startDate), new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
	}
	
	
	
	@GetMapping("/get-quantite-par-produit/{id-produit}/{start-date}/{end-date}")
	@ApiOperation("get quantite")
	@ResponseBody
	public Long getQuantiteProduitVendu(@PathVariable("id-produit") Long idProduit,@PathVariable("start-date") String startDate,@PathVariable("end-date") String endDate) throws ParseException {
		return detailFactureService.getQuantiteProduitVendu(idProduit,startDate,endDate);
		
	}

}
