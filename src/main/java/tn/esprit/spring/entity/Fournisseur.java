package tn.esprit.spring.entity;


import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Column;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "Fournisseur")
public class Fournisseur implements Serializable {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idFournisseur")
	private long idFournisseur;
	@Column(name="code")
	private String code;
	@Column(name="libelle")
	private String libelle;
	@Column(name="type")
	private String type;
	@Column(name="Telephone")
	private String Telephone;
	

}

	

//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.FieldDefaults;



//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class Fournisseur implements Serializable {
//
//	/**
//	 * 
//	 */
//	static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	Long idFournisseur;
//
//	String code;
//	String libelle;
//
//
//}
