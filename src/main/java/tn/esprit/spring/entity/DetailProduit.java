package tn.esprit.spring.entity;

import java.io.Serializable;


import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.enume.CategorieProduit;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DetailProduit implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idDetailProduit;

	@Temporal(TemporalType.DATE)
	Date dateCreation;
	
	@Temporal(TemporalType.DATE)
	Date dateDerniereModification;

	@Enumerated(EnumType.STRING)
	CategorieProduit categorieProduit;

	@OneToOne(mappedBy = "detailProduit")
	@JsonIgnore
	Produit produit;

	
	

}
