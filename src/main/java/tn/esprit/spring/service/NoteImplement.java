package tn.esprit.spring.service;

import java.util.Date;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Note;
import tn.esprit.spring.repository.NoteRepository;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.UserRepository;



@Service
public class NoteImplement implements INote{

	
	@Autowired
	NoteRepository nr;
	
	@Autowired
	ProduitRepository pr;
	
	@Autowired
	UserRepository cr;


	@Override
	public List<Note> retrieveAllNote() {
		// TODO Auto-generated method stub
		return (List<Note>) nr.findAll();
	}

	
	@Override

	public Note updateNote(float note,String commentaire,Long idProduit,Long idClient) {
		// TODO Auto-generated method stub
		Note n;
		List<Note> lista=getNoteByProduitClient(idProduit,idClient);
		n=lista.get(0);
		n.setNoote(note);
		n.setCoommentaire(commentaire);
		return nr.save(n);
	}
	
	
		
	@Override
	public void addNote1(float note,String commentaire, Long idproduit, Long idClient) {
		// TODO Auto-generated method stub
	//	Long k=AlreadyExists(n);
		//if(k==0) {
		Date date=new Date();
		//System.out.print(n.getNote());
	
		nr.ajouterNote(note,commentaire,date,idproduit,idClient);
	}

	public Note updateNote(Note n) {
		// TODO Auto-generated method stub
		return nr.save(n);
	}
	
	@Override
	public Long AlreadyExists(Note n) {
		
		List<Note> Notes=retrieveAllNote();
		// TODO Auto-generated method stub
		
		for(Note n1:Notes) {
			
			if(n1.getClient().equals(n.getClient()) && n1.getProduit().equals(n.getProduit())) {
				return n1.getIdNote();
			}
		}
		
		
		return (long) 0;
		
	}
	
	
	@Override
	public void addNote(Note n, Long idproduit, Long idClient) {
		// TODO Auto-generated method stub
	//	Long k=AlreadyExists(n);
		//if(k==0) {
//		 nr.ajouterNote(n.getNote(),n.getCommentaire(),n.getDateNote(),idproduit,idClient);


//		}
		
	/* else {
			Note n1=updateNote(n);
			k=1;
		}*/
		
	}


//	@Override
//	public List<Note>getNoteByProduitClient(Long idproduit, Long idClient) {
//		// TODO Auto-generated method stub
//		return nr.getNoteByProduitClient(idproduit,idClient);
//	}
	@Override
	public List<Note> getNoteByProduitClient(Long idproduit, Long idClient) {
		// TODO Auto-generated method stub
		System.out.print(nr.getNoteByProduitClient(idproduit, idClient));
		return nr.getNoteByProduitClient(idproduit, idClient);
	}

	@Override

	public List<Note> getNoteByClient1(Long idClient) {
		// TODO Auto-generated method stub
		return  nr.getNoteByclient(idClient);
	}

	@Override
	public Note retrieveNote(Long idNote) {
		// TODO Auto-generated method stub
		return null;
	}



	





	
	
	@Override
	public double CalculAverageNoteForProduit(Long idproduit) {
		// TODO Auto-generated method stub
		List<Note> list=nr.getNoteByProduit(idproduit);
		double somme=0.0;
		int k=0;
		double average=0.0;
		for(Note note:list) {
			
			somme+=note.getNoote();
			
			k++;
		}
		average=somme/k;
		return average;
	}


	@Override
	public List<Note> getNoteBynoote(float note) {
		// TODO Auto-generated method stub
		return nr.getNoteBynoote(note);
	}
	
	
}
