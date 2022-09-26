package tn.esprit.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "Entrepot")
public class Entrepot implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idEntrepot")
	private long idEntrepot;
	@Column(name="LieuLivraison")
	private String LieuLivraison;
	@Column(name="DateLivraison")
	private String DateLivraison;
	@Column(name="TypeProduit")
	private String TypeProduit;


	@ManyToOne
	private Fournisseur Fournisseur;


	
}
