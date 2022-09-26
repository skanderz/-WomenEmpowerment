package tn.esprit.spring.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.internal.function.text.Length;


import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.enume.CategorieClient;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired	UserRepository userRepository;
	@Autowired	RoleRepository roleRepository;


	

	@Override
	public ArrayList<User> getInvitationList(Long id) 
		 {    
		User u = retrieveUser(id);    
			  String[] arr = u.getRequestlist().split("");   int j = 0;   String[] arr2 = new String[arr.length];    
			  for(int n=0; n<(arr2.length); n++) { arr2[n]=""; } 
			  
			  ArrayList<User> Tab = new ArrayList<User>();   
			  for(int i=1; i<(arr.length); i++){   
				  if(arr[i].equalsIgnoreCase("+")) {   j++;   }  else { arr2[j] = arr2[j] + arr[i];  }}			  
			  
			  int s=0;
			  for(int p=0; p<(arr2.length); p++){ if(arr2[p].equalsIgnoreCase("")){  s=s+1;  }} 		  
			  String[] resTab = Arrays.copyOf(arr2,(arr2.length - s));    

			  for(int k=0; k<(resTab.length); k++){  Tab.add(retrieveUser((long) Integer.parseInt(resTab[k])));   }       
			  return Tab;   }     
	 
	 
	
	@Override
	public int Invitation(Long idu, Long id) {
		User u = retrieveUser(idu);  
		String x =  u.getRequestlist() + id + "+";      	
		return userRepository.Invitation(idu ,x);  }  
		
	
	
	/*******************************************************************************************************/

	
	@Override
	public ArrayList<User> getFriendList(Long id) 
		 {    
		User u = retrieveUser(id);    
			  String[] arr = u.getFriendlist().split("");   int j = 0;   String[] arr2 = new String[arr.length];    
			  for(int n=0; n<(arr2.length); n++) { arr2[n]=""; } 
			  
			  ArrayList<User> Tab = new ArrayList<User>();  
			  for(int i=1; i<(arr.length); i++){   
				  if(arr[i].equalsIgnoreCase("+")) {   j++;   }  else { arr2[j] = arr2[j] + arr[i];  }}			  
			  
			  int s=0;
			  for(int p=0; p<(arr2.length); p++){ if(arr2[p].equalsIgnoreCase("")){  s=s+1;  }} 		  
			  String[] resTab = Arrays.copyOf(arr2,(arr2.length - s));    

			  for(int k=0; k<(resTab.length); k++){  Tab.add(retrieveUser((long) Integer.parseInt(resTab[k])));   }       
			  return Tab;   }     
	
	
	
	
	@Override
	public int AcceptInvitation(Long idu, Long id) {
		User u = retrieveUser(idu);   String x ="";  String y="+";
		String[] arr = u.getRequestlist().split("");  int j = 0;   String[] arr2 = new String[arr.length];    
		for(int n=0; n<(arr2.length); n++) {  arr2[n]="";  } 
		  
		ArrayList<User> Tab = new ArrayList<User>();  
		  for(int i=1; i<(arr.length); i++){   
			  if(arr[i].equalsIgnoreCase("+")) {   j++;   }  else { arr2[j] = arr2[j] + arr[i];  }}	
		
		  int s=0;
		  for(int p=0; p<(arr2.length); p++){ if(arr2[p].equalsIgnoreCase("")){  s=s+1;  }} 		  
		  String[] resTab = Arrays.copyOf(arr2,(arr2.length - s));  
		  
		  for(int k=0; k<(resTab.length); k++){   
			  if( ((long) Integer.parseInt(resTab[k])) == id){  x = u.getFriendlist() + resTab[k] + "+";  }}  
		  		  
		  for( int m=0; m<(resTab.length); m++ ){  if((long) Integer.parseInt(resTab[m]) == id)  continue;
		                                                     else y = y + resTab[m] + "+";  System.out.println(y);}		  
		return userRepository.AcceptInvitation(idu ,x ,y);  }    
	
	

	
	@Override
	public int RefuseInvitation(Long idu, Long id) {
	
		User u = retrieveUser(idu);   String x ="";  String y="+";
		String[] arr = u.getRequestlist().split("");  int j = 0;   String[] arr2 = new String[arr.length];    
		for(int n=0; n<(arr2.length); n++) {  arr2[n]="";  } 
		  
		ArrayList<User> Tab = new ArrayList<User>();  
		  for(int i=1; i<(arr.length); i++){   
			  if(arr[i].equalsIgnoreCase("+")) {   j++;   }  else { arr2[j] = arr2[j] + arr[i];  }}	
		
		  int s=0;
		  for(int p=0; p<(arr2.length); p++){ if(arr2[p].equalsIgnoreCase("")){  s=s+1;  }} 		  
		  String[] resTab = Arrays.copyOf(arr2,(arr2.length - s));  
		  
		  for( int m=0; m<(resTab.length); m++ ){  if((long) Integer.parseInt(resTab[m]) == id)  continue;
          else y = y + resTab[m] + "+";  System.out.println(y);}  

		return userRepository.RefuseInvitation(idu ,y);  }     
	
	
	
	@Override
	public int RetirerAmis(Long idu, Long id) {
	
		User u = retrieveUser(idu);   String x ="";  String y="+";
		String[] arr = u.getFriendlist().split("");  int j = 0;   String[] arr2 = new String[arr.length];    
		for(int n=0; n<(arr2.length); n++) {  arr2[n]="";  } 
		  
		ArrayList<User> Tab = new ArrayList<User>();  
		  for(int i=1; i<(arr.length); i++){   
			  if(arr[i].equalsIgnoreCase("+")) {   j++;   }  else { arr2[j] = arr2[j] + arr[i];  }}	
		
		  int s=0;
		  for(int p=0; p<(arr2.length); p++){ if(arr2[p].equalsIgnoreCase("")){  s=s+1;  }} 		  
		  String[] resTab = Arrays.copyOf(arr2,(arr2.length - s));  
		  
		  for( int m=0; m<(resTab.length); m++ ){  if((long) Integer.parseInt(resTab[m]) == id)  continue;
          else y = y + resTab[m] + "+";  System.out.println(y);}  

		return userRepository.RetirerAmis(idu ,y);  }     
	
	
	
	/*******************************************************************************************************/
	
	@Override
	public List<User> retrieveAllUsers() {	return (List<User>) userRepository.findAll();	}

	
	
	@Override
	public Page<User> findByEmailContaining(String email, Pageable pageable) { 
	return userRepository.findByEmailContaining(email,pageable); }

	
	
	
	@Override
	public Page<User> findByEmailContainingAndCategorieClientContaining(String email, String categorie, Pageable pageable) {
		CategorieClient	cat = CategorieClient.valueOf(categorie) ;
		return userRepository.findByEmailContainingAndCategorieClientIs(email,cat,pageable);	}

	
	@Override
	public Page<User> pageAll(Pageable pageable) {	return userRepository.findAll(pageable);	}

	
	
	
	@Override
	public boolean assignAdmin(Long id) {
		Role r = this.roleRepository.getRoleAdmin();
		User u = this.userRepository.getById(id);
		if (!( u.getRoles().contains(r))) {
		u.getRoles().add(r);
		this.userRepository.saveAndFlush(u);
		return true ;} else {	return false ;}}
	
	

	@Override
	public boolean withholdAdmin(Long id) {
		Role r = this.roleRepository.getRoleAdmin();
		User u = this.userRepository.getById(id);
		if ( u.getRoles().contains(r)) {  u.getRoles().remove(r);
			this.userRepository.saveAndFlush(u);
			return true ;} else  { return false; }}
	
	

	@Override public User addUser(User c) {	return userRepository.save(c);	}
	@Override public void deleteUser(Long id) {	userRepository.deleteById(id);	}
	@Override public User updateUser(User c) {	return userRepository.save(c);	}
	@Override public User retrieveUser(Long id) {	return userRepository.findById(id).orElse(null);	}
    @Override public List<User> getClientWithDate(Date d1 ,Date d2) {return userRepository.retrieveClientsByDateNaissance(d1,d2);}             
	
    @Override  public int ChangePassword(Long idUser,String Password) { return  userRepository.ChangePassword(idUser,Password);}            
    @Override  public int AddSolde(Long idUser,double AddedSolde)     { return  userRepository.AddSolde(idUser,AddedSolde);}           



    @Override   public int DisableAccount(Long idUser) { return  userRepository.DisableAccount(idUser);   }
	@Override   public int EnableAccount(Long idUser)  { return  userRepository.EnableAccount(idUser);   }





	



	



	
	

	
	
		
	

}
