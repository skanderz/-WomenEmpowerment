package tn.esprit.spring.entity;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Stock implements Serializable {
	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idStock;

	int qte;

	int qteMin;

	String libelleStock;
	
	@Temporal(TemporalType.DATE)
	Date createdAt;
	
	@Temporal(TemporalType.DATE)
	Date updatedAt;
	
	boolean checked;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "stock")
	Set<Produit> produitList;

	

	
}
