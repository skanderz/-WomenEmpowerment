package tn.esprit.spring.repository;

import java.util.Date;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository <Subscription, Long> ,JpaRepository <Subscription, Long>{
	

	
	@Transactional
	@Modifying
	@Query("update Subscription s set s.dateFin= :dateFin where s.idSub= :idSub")
	int ExtendSubscription(@Param("idSub") Long idSub ,@Param("dateFin") Date dateFin);
	
	
	@Transactional
	@Modifying
	@Query("update Subscription s set s.dateFin= :dateFin where s.idSub= :idSub")
	int  ChangeToPremium(@Param("idSub") Long idSub ,@Param("dateFin") Date dateFin);
	
	@Transactional
	@Modifying
	@Query("update Subscription s set s.typeSub='Basic' where s.idSub= :idSub")
	int  ChangeToBasic(@Param("idSub") Long idSub);
	
	
	
	
	 
	 
	
	
	

}

