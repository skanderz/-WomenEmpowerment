package tn.esprit.spring.entity;

import java.io.Serializable;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.enume.CategorieClient;
import tn.esprit.spring.enume.Profession;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)  Long iduser;
	
	double solde;
	String requestlist;   String friendlist;
	boolean status; 
	@JsonIgnore int identifiant ;
	String nom;
	String prenom;
	@Column
	String username;
	@Temporal(TemporalType.DATE)  Date dateNaissance;
	String email;
	String password;
	@Enumerated(EnumType.STRING)  CategorieClient categorieClient;
	@Enumerated(EnumType.STRING)  Profession profession;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="user_roles" ,joinColumns = @JoinColumn(name="id_client") ,inverseJoinColumns = @JoinColumn(name="id"))
	private Set<Role> roles = new HashSet<>();

	
     @OneToOne(cascade = CascadeType.ALL)  private Subscription Subscription; 
    
  
   
	
	
	
	public User(String nom,String prenom,String username,String email,String password,Date dateNaissance){
		this.nom = nom;	this.prenom =prenom ;	this.username = username;	this.email = email;	this.password = password;	this.dateNaissance = dateNaissance ;	}     
	
	public User(String nom,String prenom,String username,String email,String password,Date dateNaissance,CategorieClient cat,
				Profession prof ,float solde , String FriendList ,String RequestList){
		this.nom = nom;	this.prenom =prenom ;	this.username = username;	this.email = email;	this.password = password;
		this.dateNaissance = dateNaissance ;	this.categorieClient = cat;	this.profession = prof;  this.solde =solde;
		this.friendlist =FriendList; this.requestlist =RequestList;}
	
	
	@Override
	public String toString() {
		return "Client [iduser=" + iduser + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance="	+ dateNaissance + ", email=" + email + "]";
	}

	

	

}
