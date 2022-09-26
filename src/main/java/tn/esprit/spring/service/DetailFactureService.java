package tn.esprit.spring.service;

import java.text.ParseException;

import java.util.Date;

import tn.esprit.spring.entity.DetailFacture;

public interface DetailFactureService {

	DetailFacture addDetailFacture(DetailFacture detailFacture );
	

//	double getRevenuBrutProduit(Long idProduit, Date startDate, Date endDate);

	Long getQuantiteProduitVendu(Long idProduit, String startDate, String endDate) throws ParseException;

	double getRevenuBrutProduit(Long idProduit, Date startDate, Date endDate);

}
