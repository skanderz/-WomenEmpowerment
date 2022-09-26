package tn.esprit.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

 
import tn.esprit.spring.entity.User;
import tn.esprit.spring.enume.CategorieClient;
 
import java.util.Date;
import java.util.List; 
import java.util.Optional;
 
import javax.transaction.Transactional;   

@Repository
public interface UserRepository extends JpaRepository<User, Long> ,CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByIduser(int iduser);
   // List<User> findByStatus(boolean etat);
   // Boolean existsByUsername2(String id); 
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
    //Optional<ERole> findByRole(ERole role);
  
	Page<User> findByEmailContainingAndCategorieClientIs(String email,CategorieClient categorie,Pageable pageable);

	
	
	
	//Page<User> findByEmailContainingAndCategorieClientIs(String email,ERole categorie,Pageable pageable);
	Page<User> findByEmailContaining(String email,Pageable pageable);
	void findByDateNaissanceGreaterThan(Date dateN);
	
    
    @Transactional
	@Modifying
	@Query("update User u set u.status=FALSE where u.iduser= :iduser")
	int DisableAccount(@Param("iduser") Long iduser);
    
    
    @Transactional
 	@Modifying
 	@Query("update User u set u.status=FALSE where u.iduser= :iduser")
 	int EnableAccount(@Param("iduser") Long iduser);
	
    
    @Transactional
 	@Modifying
 	@Query("update User u set u.password=:password where u.iduser= :iduser")
 	int ChangePassword(@Param("iduser") Long iduser ,@Param("password") String password);
    
    
    @Transactional
 	@Modifying
 	@Query("update User u set u.solde=:solde where u.iduser= :iduser")
 	int AddSolde(@Param("iduser") Long iduser ,@Param("solde") double solde);
    
    
    @Query("SELECT c FROM User c WHERE c.dateNaissance BETWEEN :d1 and :d2 ")
	List<User> retrieveClientsByDateNaissance(@Param("d1") Date d1 , @Param("d2") Date d2);
    
    
    @Transactional
 	@Modifying
 	@Query("update User u set u.requestlist=:requestlist where u.iduser= :iduser") 
 	int Invitation(@Param("iduser") Long idu ,@Param("requestlist") String requestlist);
    
    
    @Transactional
 	@Modifying
 	@Query("update User u set u.friendlist=:friendlist ,u.requestlist=:requestlist where u.iduser= :iduser")  
 	int AcceptInvitation(@Param("iduser") Long idu ,@Param("friendlist") String friendlist ,@Param("requestlist") String requestlist);   
	
	
    @Transactional
   	@Modifying
   	@Query("update User u set u.requestlist=:requestlist where u.iduser= :iduser")  
   	int RefuseInvitation(@Param("iduser") Long idu ,@Param("requestlist") String requestlist);   
  	
    
    @Transactional
   	@Modifying
   	@Query("update User u set u.friendlist=:friendlist where u.iduser= :iduser")  
   	int RetirerAmis(@Param("iduser") Long idu ,@Param("friendlist") String friendlist);   
  	
    
    
}
