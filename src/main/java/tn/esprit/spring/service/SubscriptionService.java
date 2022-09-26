package tn.esprit.spring.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Subscription;


public interface SubscriptionService{

	List<Subscription> retrieveAllSubscription();
	Subscription addSubscription(Subscription c);
	void deleteSubscription(Long id);
	Subscription retrieveSubscription(Long id);
	int ExtendSubscription(Long idSub ,Date DateFin);
    int ChangeToPremium(Long idSub ,Date dateFin);
    int ChangeToBasic(Long idSub);
    
	
	
    
	
	
	



}
