package tn.esprit.spring.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.google.zxing.WriterException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.enume.CategorieClient;
import tn.esprit.spring.service.UserService;

@Api(tags = "User management")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserRestController {   

	@Autowired	UserService userService;
	private static String  QR_CODE_IMAGE_PATH ="path";
	
	 
	public int nbrRandom()
	{   
		List<User> UL = userService.retrieveAllUsers();
		Random random = new Random();   boolean test=false;    int t=0;
	    int nb = 0 ,borneInf = 10000000 ,borneSup = 99999999; 
		while (test==false){   nb = borneInf+random.nextInt(borneSup-borneInf);
	    for(User u : UL){      if(nb == u.getIdentifiant())  t=1; }  if(t==0) test=true;   }     
		return nb;  }
	
	
	
	@PostMapping("/add-User")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@ResponseBody
	public User addUser(@RequestBody User u)  {  
		   QR_CODE_IMAGE_PATH = "./src/main/resources/QR-CODE-USERLIST/" + "QRCode - "+  u.getUsername()  + ".png";
		   try {QRCodeGenerator2.generateQRCodeImage( u.getUsername(), 500, 500, QR_CODE_IMAGE_PATH); } 
		   catch (WriterException | IOException e) {e.printStackTrace();}              
		int idnbr = nbrRandom();       u.setIdentifiant(idnbr);	 
		ESubscription Basic = ESubscription.Basic;   u.getSubscription().setTypeSub(Basic); 
		Date date =  u.getSubscription().getDateDebut();
		Calendar c = Calendar.getInstance();     c.setTime(date);  
		c.add(Calendar.DATE, 15);		date = c.getTime();  
	    u.setSolde(100);  u.getSubscription().setDateFin(date); u.setRequestlist("+"); u.setFriendlist("+");
		return userService.addUser(u); }    

	
	// THIS IS NOT A SIGNUP
		@PostMapping("/add-user")
		@ApiOperation(value = "ajouter user")
		@PreAuthorize("hasRole('ADMIN')")
		@ResponseBody
		public User addClient(@RequestBody User c) {	User client = userService.addUser(c);	return client; }

		
	
	//  http://localhost:8089/SpringMVC/user/Solde/{idUser}/{AddedSolde}
	@PutMapping("/Solde/{idUser}/{AddedSolde}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@ResponseBody
	public int AddSolde(@PathVariable Long idUser ,@PathVariable("AddedSolde") double AddedSolde ) {	
		List<User> UL = userService.retrieveAllUsers();  double s=0;
		for (User u : UL) { if(u.getIduser() == idUser) {  s=u.getSolde() + AddedSolde; }}
		return userService.AddSolde(idUser,s);}
	
	
	
	@PutMapping("/ChangePassword/{idUser}/{OldPassword}/{NewPassword}/{VerifNewPassword}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@ResponseBody
	public int ChangePassword(@PathVariable Long idUser ,@PathVariable(name = "OldPassword") String OldPassword 
		                     ,@PathVariable(name = "NewPassword") String NewPassword ,@PathVariable(name = "VerifNewPassword") String VerifNewPassword   )
	{	
	List<User> UL = userService.retrieveAllUsers();  int test = 0; int test2 = 0 ;
	   if (NewPassword.length() < 6) {  throw new ArithmeticException("le mot de passe doit contenir au minimum 6 caractere !!");  }
	     for (User u : UL) { if(u.getIduser() == idUser)  { if (u.getPassword().equals(OldPassword)) test=1; }}
	     if (test==0) {  throw new ArithmeticException("Ancient mot de passe saisie incorrect");  }
	       if ( VerifNewPassword.equals(NewPassword) )  test2 = 1 ;   
	       if ( test2 == 0 ) {  throw new ArithmeticException("Confirmation du mot de passe incorrect");}	
	return  userService.ChangePassword(idUser ,NewPassword);	}
	
	
	
	
	/**************************************************************************************/
	
	 @GetMapping("/getInvitationList/{userid}")
	 @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	 @ResponseBody
	 public ArrayList<User> getInvitationList(@PathVariable("userid") Long userid )
	 { return (ArrayList<User>) userService.getInvitationList(userid); }
	
	 
	 
	
	@PutMapping("/Invitation/{userid}/{idselected}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@ResponseBody
	public int Invitation(@PathVariable("userid") Long userid ,@PathVariable("idselected") Long idselected)
	{  return userService.Invitation(userid,idselected)  ;}

	
	
	
	 @GetMapping("/getFriendList/{userid}")
	 @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	 @ResponseBody
	 public ArrayList<User> getFriendList(@PathVariable("userid") Long userid )
	 { return (ArrayList<User>) userService.getFriendList(userid); }
	
	 
	
	@PutMapping("/Accept/{userid}/{idselected}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@ResponseBody
	public int AcceptInvitation(@PathVariable("userid") Long userid ,@PathVariable("idselected") Long idselected)
	{  return userService.AcceptInvitation(userid,idselected)  ;}

	
	
	@PutMapping("/Refuse/{userid}/{idselected}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@ResponseBody
	public int RefuseInvitation(@PathVariable("userid") Long userid ,@PathVariable("idselected") Long idselected)
	{  return userService.RefuseInvitation(userid,idselected)  ;}
	
	
	@PutMapping("/RetirerAmis/{userid}/{idselected}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@ResponseBody
	public int RetirerAmis(@PathVariable("userid") Long userid ,@PathVariable("idselected") Long idselected)
	{  return userService.RetirerAmis(userid,idselected)  ;}
	
	
	/**************************************************************************************/
	
	@PutMapping("/withhold-admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> withholdAdmin(@RequestBody String id) {
	if ( 	userService.withholdAdmin(Long.valueOf(id)) ) {
	return new ResponseEntity<>(  "DELETED FROM ADMIN ",	HttpStatus.OK); } 
	else {return new ResponseEntity<>(	"ALREADY SIMPLE USER",	HttpStatus.BAD_REQUEST);}
	}

	
	
	
	@PutMapping("/DisableAccount/{idUser}")
	@ResponseBody
	public int DisableAccount(@PathVariable Long idUser  ) { return  userService.DisableAccount(idUser);	}
	
	
	
	@ApiOperation(value = "Récupérer la liste des utilisateurs")
	@GetMapping("/retrieve-all-user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@ResponseBody
	public List<User> listUser() { return userService.retrieveAllUsers();	}

	

	@ApiOperation(value = "Pagination des utilisateurs")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/paginate/users")
	public ResponseEntity<Map<String, Object>> getAllTutorials(
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String categorie,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size
	 ){
		try {
			List<User> users = new ArrayList<User>();
			Pageable paging = PageRequest.of(page, size);

			Page<User> pageTuts;
			if (email == null && categorie == null ) {	pageTuts = userService.pageAll(paging);} 
			else if (categorie == null){
				pageTuts = userService.findByEmailContaining(email, paging);
			} else  if (email == null) {
				pageTuts = userService.findByEmailContainingAndCategorieClientContaining("", categorie, paging);
				System.out.println("Called find by cat");
				} else {
				pageTuts = userService.findByEmailContainingAndCategorieClientContaining(email, categorie, paging);
				System.out.println("Called find by email and cat");
			}

				users = pageTuts.getContent();

				Map<String, Object> response = new HashMap<>();
				response.put("users", users);
				response.put("currentPage", pageTuts.getNumber());
				response.put("totalItems", pageTuts.getTotalElements());
				response.put("totalPages", pageTuts.getTotalPages());

				return new ResponseEntity<>(response, HttpStatus.OK);
			} catch(Exception e){
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

	}



	@GetMapping("/retrieve-user/{user-id}")
	@ApiOperation(value = "Récupérer client par id")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@ResponseBody
	public User retrieveUser(@PathVariable("user-id") String clientId) {return userService.retrieveUser(Long.valueOf(clientId));}

	


	@DeleteMapping("/remove-client/{client-id}")
	@ApiOperation(value = "supprimer client")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')") //TO SECURE //TO SECURE //TO SECURE
	@ResponseBody
	public void removeClient(@PathVariable("client-id") Long clientId) {  userService.deleteUser(clientId);	}


	@PutMapping("/modify-client")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@ResponseBody
	public User modifyClient(@RequestBody User client) {  return userService.updateUser(client);  }

	
	@PutMapping("/assign-admin")
	@PreAuthorize("hasRole('ADMIN')") 
	public ResponseEntity<String> assignAdmin(@RequestBody String id) {
		if (  userService.assignAdmin(Long.valueOf(id)) ) {
		return new ResponseEntity<>(	"ASSIGNED",	HttpStatus.OK);} 
		else {	return new ResponseEntity<>(	"ALREADY ADMIN",	HttpStatus.BAD_REQUEST);	}}
	
	
	
	
	
	

	
	@PutMapping("/EnableAccount/{idUser}")
	@ResponseBody
	public int EnableAccount(@PathVariable Long idUser  ) {	return  userService.EnableAccount(idUser);	}


	
	  
	
	
}
