package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//@ToString
public class Produit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idProduit;

	String code;

	String libelle;

	float prixUnitaire;


	double NoteMoyenne;

	@OneToMany(mappedBy = "produit" ,cascade = CascadeType.ALL)
	@JsonIgnore
	Set<DetailFacture> detailFactureList;

	@OneToOne
	@JoinColumn(name = "detailProduitId")
	DetailProduit detailProduit;

	@OneToOne
	@JoinColumn(name = "ImageId")
	ImageModel Image;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "stockId")
	Stock stock;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "rayonId")
	Rayon rayon;

	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	Set<Fournisseur> fournisseur;

}
