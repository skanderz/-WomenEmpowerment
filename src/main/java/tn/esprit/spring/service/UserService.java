package tn.esprit.spring.service;


import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.enume.CategorieClient;

public interface UserService {



	User addUser(User c);
	void deleteUser(Long id);
	User updateUser(User c);
	User retrieveUser(Long id);
	List <User> getClientWithDate(Date d1 ,Date d2);

	List<User> retrieveAllUsers();
	Page<User> findByEmailContaining(String email, Pageable pageable);
	Page<User> findByEmailContainingAndCategorieClientContaining(String email, String categorie, Pageable pageable);
	Page<User> pageAll(Pageable pageable);


	int Invitation(Long idu ,Long id);
	
	

	boolean assignAdmin( Long id);
	boolean withholdAdmin(Long id) ;
	
	int ChangePassword(Long idUser,String Password);
	int AddSolde(Long idUser,double s);

	int DisableAccount(Long idUser);   
	int EnableAccount(Long idUser);
	List<User> getInvitationList(Long id);
	/*  int RefuseInvitation(Long idu, Long id);   */
	int AcceptInvitation(Long idu, Long id);
	ArrayList<User> getFriendList(Long id);
	int RefuseInvitation(Long idu, Long id);
	int RetirerAmis(Long idu, Long id);

	
/*	List<User> retrieveAlladmins();
	List<User> retrieveAllClients();*/
}
