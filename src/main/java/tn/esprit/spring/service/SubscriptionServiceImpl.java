package tn.esprit.spring.service;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.controller.*;
import tn.esprit.spring.repository.*;
import tn.esprit.spring.service.*; 


@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	
@Autowired	 SubscriptionRepository SubscriptionRepo;
@Autowired   UserService UserService;
@Autowired   SubscriptionController SubscriptionController;

	
@Override	public List<Subscription> retrieveAllSubscription() {	return (List<Subscription>) this.SubscriptionRepo.findAll();}
@Override	public Subscription addSubscription(Subscription c) {	return this.SubscriptionRepo.save(c);	}
@Override	public void deleteSubscription(Long id)             {	this.SubscriptionRepo.deleteById(id);	}
@Override	public Subscription retrieveSubscription(Long id)   {	return this.SubscriptionRepo.findById(id).orElse(null);	}
	


@Override   public  int ExtendSubscription(Long idSub,Date dateFin) { return  SubscriptionRepo.ExtendSubscription(idSub ,dateFin);}
@Override   public  int ChangeToPremium(Long idSub ,Date dateFin) { return SubscriptionRepo.ChangeToPremium(idSub ,dateFin);   }
@Override   public  int ChangeToBasic(Long idSub) { return SubscriptionRepo.ChangeToBasic(idSub);   }


 
@Scheduled(fixedRate = 60000)  
public void fixedRateMethod() { 
	 List<User> UL = UserService.retrieveAllUsers();   long x = 0 ,y = 0 ;String str ="";
	 for (User u : UL){  	ESubscription Prem = ESubscription.Premuim;   ESubscription Basic = ESubscription.Basic; 
	 if ( u.getSubscription() == null ) continue;  
	 else if ( u.getSubscription().getTypeSub().equals(Prem) ) {
			 	  
x = SubscriptionController.CalculDateBetweenTwoDates(u.getSubscription().getIdSub() , u.getSubscription().getDateFin());
y = x ;str = String.valueOf(x) ; 
log.info( " \n \n \n " + "   ******  Date d'expiration de l'abonnement du compte '" + u.getUsername() + "' : " + u.getSubscription().getDateFin()             
+ "  ******\n" + "                       ******  il vous reste : " +  str  + " Jours   ****** \n \n ");    			}
   }}    





}




