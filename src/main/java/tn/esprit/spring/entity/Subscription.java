package tn.esprit.spring.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.spring.entity.Subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Subscription {
	@Id     @GeneratedValue(strategy = GenerationType.IDENTITY)	private Long idSub;
	@Temporal(TemporalType.DATE)	                private Date dateDebut;
	@JsonIgnore @Temporal(TemporalType.DATE)        private Date dateFin;
    @Enumerated(EnumType.STRING)                    private ESubscription  typeSub;
    
    @JsonIgnore @OneToOne(mappedBy="Subscription") 	private User Userrr;   
}



