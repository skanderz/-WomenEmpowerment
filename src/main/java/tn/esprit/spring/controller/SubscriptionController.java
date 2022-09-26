package tn.esprit.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.service.SubscriptionService;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.SubscriptionRepository;
import tn.esprit.spring.entity.ESubscription;
import tn.esprit.spring.entity.Subscription;
import tn.esprit.spring.service.UserService;
import tn.esprit.spring.service.SubscriptionService;



@RestController
@RequestMapping("/Subscription")
public class SubscriptionController {

	
	
  @Autowired   SubscriptionService SubscriptionService;
  @Autowired   SubscriptionRepository SubscriptionRepo;
  @Autowired   UserService UserService;




  public long CalculDateBetweenTwoDatesExtend(Long idSub ,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date df )  { 
		List<User> UL = UserService.retrieveAllUsers();  
		long res=0;  Date db = new Date();  	              
		
		for (User u : UL) { if(u.getSubscription().getIdSub() == idSub) { 
			
	try {    		 db = u.getSubscription().getDateFin();		  
		      long diff = df.getTime() - db.getTime();           
		      res = (diff / (1000*60*60*24));        			}
	catch (Exception e) {  e.printStackTrace();  }  

	}}
	return res ;   }   

  
  
  
  public long CalculDateBetweenTwoDates(Long idSub ,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date df )  { 
		List<User> UL = UserService.retrieveAllUsers();  
		long res=0;  Date db = new Date();		              // Date df = new Date();
		
	for (User u : UL) { if(u.getSubscription().getIdSub() == idSub) { 			
	try {     db = u.getSubscription().getDateDebut();		  //df = u.getSubscription().getDateFin();
		      long diff = df.getTime() - db.getTime();           
		      res = (diff / (1000*60*60*24));        			}
	catch (Exception e) {  e.printStackTrace();  }  
	}}	return res ;   }   
  
  
  
@PutMapping("/ChangeToPremium/{idSub}/{dateFin}")
@ResponseBody
public int ChangeToPremium(@PathVariable Long idSub ,@PathVariable(name = "dateFin")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFin) {	 
	List<User> UL = UserService.retrieveAllUsers();  double s=0 ,calculdate=0;
	
	for (User u : UL) { if(u.getSubscription().getIdSub() == idSub) {  
ESubscription Prem = ESubscription.Premuim;   
if ( u.getSubscription().getTypeSub().equals(Prem) ) {  throw new ArithmeticException("le compte est deja premium  ");  }

calculdate = CalculDateBetweenTwoDates(idSub ,dateFin);     
s=( u.getSolde() - (calculdate * 1.2) ); 
if ( s < 0 ) { throw new ArithmeticException("Solde Insuffisant"); } 
else { u.setSolde(s); u.getSubscription().setTypeSub(Prem); }                                            }}
	
return SubscriptionService.ChangeToPremium(idSub ,dateFin);	}
 




//http://localhost:8089/SpringMVC/client/modify-client
@PutMapping("/ExtendSubscription/{idSub}/{dateFin}")
@ResponseBody
public int ExtendSubscription(@PathVariable Long idSub ,@PathVariable(name = "dateFin")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFin) {
   List<User> UL = UserService.retrieveAllUsers();  double s=0 ,calculdate=0;
   for (User u : UL) { if(u.getSubscription().getIdSub() == idSub) {  
ESubscription Basic = ESubscription.Basic;  
if ( u.getSubscription().getTypeSub().equals(Basic) ) {  throw new ArithmeticException("le compte n'est pas premium  ");}
     
    calculdate = CalculDateBetweenTwoDatesExtend(idSub ,dateFin);     	s=( u.getSolde() - (calculdate * 1.2) ); 
    if ( s < 0 ) {   throw new ArithmeticException("Solde Insuffisant");   }     else   { u.setSolde(s); }   }}
			
	return SubscriptionService.ExtendSubscription(idSub,dateFin);		}  
		










@GetMapping("/retrieve-all-Subscriptions")
@ResponseBody
public List<Subscription> getSubscriptions() {  List<Subscription> listSubscriptions = SubscriptionService.retrieveAllSubscription();
                                             return listSubscriptions;  } 



@GetMapping("/retrieve-Subscription/{Subscription-id}")
@ResponseBody
public Subscription retrieveSubscription(@PathVariable("Subscription-id") Long SubscriptionId) { 
return SubscriptionService.retrieveSubscription(SubscriptionId);   }




@PostMapping("/add-Subscription")
@ResponseBody
public Subscription addSubscription(@RequestBody Subscription c) { 	return SubscriptionService.addSubscription(c); }



@DeleteMapping("/remove-Subscription/{Subscription-id}")
@ResponseBody
public void removeSubscription(@PathVariable("Subscription-id") Long SubscriptionId) { SubscriptionService.deleteSubscription(SubscriptionId); }


@PutMapping("/ChangeToBasic/{idSub}")
@ResponseBody
public int ChangeToBasic(@PathVariable Long idSub ) {	 return SubscriptionService.ChangeToBasic(idSub);	}



}






