package tn.esprit.spring.controller;

import java.util.Date;



import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Note;

import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.INote;
import tn.esprit.spring.service.ProduitService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@RequestMapping("/note")
public class NoteController {

	
	
	@Autowired
	INote NoteService;

	@Autowired
	UserRepository ur;
	@Autowired
	ProduitService produitService;
	
	/*
	@PostMapping("/add-note/{id_produit}/{id_client}")
	@ResponseBody
	public void ajouterNote(@RequestBody Note n,@PathVariable("id_produit") Long idProduit)
	{
		UserDetailsImpl userdetails = null;
		Long idclient=userdetails.getId();
=======
	
	
	@PostMapping("/add-note/{id_produit}/{id_client}")
	@ResponseBody
	public void ajouterNote(@RequestBody Note n,@PathVariable("id_client") Long idclient,@PathVariable("id_produit") Long idProduit)
	{
>>>>>>> master
		
		 NoteService.addNote(n, idProduit, idclient);
		
	}
<<<<<<< HEAD
*/
	
	
	
	@PostMapping("/add-note/{id_produit}/{commentaire}/{note}")
	@ResponseBody
	public void ajouterNote(@RequestBody Note n,@PathVariable("id_produit") Long idProduit,@PathVariable("note") float note,@PathVariable("commentaire") String commentaire)
	{
		
		
		System.out.print(n.getNoote());
		UserDetails userdetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long idClient=ur.findByUsername(userdetails.getUsername()).get().getIduser();
		Produit p=produitService.retrieveProduit(idProduit);
		
		List<Note> Lizst=getNoteByProduitClient(idProduit,idClient);
if (Lizst.size()==0) {
		System.out.print(idClient);
		 NoteService.addNote1(note,commentaire,idProduit, idClient);	
}
else {
	 NoteService.updateNote(note,commentaire,idProduit,idClient);
}
	}

	@GetMapping("/retrieve-all-notes-By-Clients/{id_client}")
	@ResponseBody
	public List<Note> getNoteByclient(@PathVariable("id_client") Long idclient){
		
		return NoteService.getNoteByClient1(idclient);
	}


	
	@GetMapping("/retrieve-all-notes-By-Clients-produits/{id_produit}/{id_client}")
	@ResponseBody
  	public List<Note> getNoteByProduitClient(@PathVariable("id_produit") Long idproduit,@PathVariable("id_client") Long idclient)
	{  
		return (List<Note>) NoteService.getNoteByProduitClient(idproduit,idclient);
	}	
	
	

	@GetMapping("/average/{id_produit}")
	@ResponseBody
  	public double CalculAverageNoteForProduit(@PathVariable("id_produit") Long idproduit)
	{  
		return NoteService.CalculAverageNoteForProduit(idproduit);
	}	
	
	@GetMapping("/retrieve-all-notes-By-noote/{note}")
	@ResponseBody
  	public List<Note> getNoteBynoote(@PathVariable("note") float note)
	{  
		return (List<Note>) NoteService.getNoteBynoote(note);
		
	}	

	

	
	
	
	
	
}
