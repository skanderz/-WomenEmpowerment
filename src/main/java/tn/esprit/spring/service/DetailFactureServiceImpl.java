package tn.esprit.spring.service;


import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.Produit;

import tn.esprit.spring.repository.DetailFactureRepository;

@Service
public class DetailFactureServiceImpl implements DetailFactureService {
	
	@Autowired
	DetailFactureRepository detailFactureRepository;

	@Override
	public DetailFacture addDetailFacture(DetailFacture detailFacture) {
		// TODO Auto-generated method stub
		return detailFactureRepository.save(detailFacture);
	}

	
	

	@Override
	public Long getQuantiteProduitVendu(Long idProduit, String startDate, String endDate) throws ParseException {
		// TODO Auto-generated method stub
		Long quantite=(long) 0;
		List<DetailFacture> LDF=new ArrayList<DetailFacture>();
				LDF=detailFactureRepository.getDetailsFacturebyprod(idProduit, new SimpleDateFormat("yyyy-MM-dd").parse(startDate), new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
				
				
				for(DetailFacture df:LDF) {
			
					System.out.println(df);
					quantite+=df.getQte();
			
				}
				
				
				
				
				return quantite;
				
	}
	
	
	
	
	

	@Override
	public double getRevenuBrutProduit(Long idProduit, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		double Revenu=0;
		List<DetailFacture> LDF=new ArrayList<DetailFacture>();
				LDF=detailFactureRepository.getDetailsFacturebyprod(idProduit, startDate, endDate);
	
				
				System.out.println("serviceimpl");
			
				
				System.out.println(idProduit);
				System.out.println(endDate);
				System.out.println(startDate);
				
				
				
				
		System.out.println(LDF);
		for(DetailFacture df:LDF) {
			System.out.println("aaaaaaaaaa");
			System.out.println(df);
			Revenu+=(df.getPrixTotal()*df.getQte())-((df.getMontantRemise()*df.getPourcentageRemise()/100));
			System.out.println(Revenu);
		    
		}
		
		return Revenu;
	}
	
	

//	@Override
//	public float getRevenuBrutProduit(Long idProduit, Date startDate, Date endDate) {
//		// TODO Auto-generated method stub
//		return detailFactureRepository.getRevenuBrutProduit(idProduit, startDate, endDate);
//	}


}
