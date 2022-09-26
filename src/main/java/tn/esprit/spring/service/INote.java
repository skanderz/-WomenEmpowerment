package tn.esprit.spring.service;

import java.util.List;



import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Note;


@Service
public interface INote {

	List<Note> retrieveAllNote();

	Note updateNote(Note n);

	Long AlreadyExists(Note n);

	Note retrieveNote(Long idNote);

	//List<Note> getNoteByProduitClient(Long idproduit,Long idClient);

	
	List<Note> getNoteByClient1(Long idClient);

	void addNote1(float note, String commentaire, Long idproduit, Long idClient);

	List<Note> getNoteByProduitClient(Long idproduit, Long idClient);


	void addNote(Note n, Long idproduit, Long idClient);


	//Note updateNote(float note, String commentaire);


	Note updateNote(float note, String commentaire, Long idProduit, Long idClient);


	double CalculAverageNoteForProduit(Long idproduit);
	
	List<Note> getNoteBynoote(float note);


	//List<Note> getNoteByProduitClient(Long idproduit, String username);
	
}
