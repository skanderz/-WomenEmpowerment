package tn.esprit.spring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.RoleServiceImpl;
import tn.esprit.spring.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ClientServiceImplTest {

	@Autowired
	UserServiceImpl clientServiceImp;
	@Autowired
	RoleServiceImpl roleService;

/*
@Test
public void testGetUserByRole(){
List<User> l = this.clientServiceImp.retrieveAllClients();
assertNotNull(l);
	for (User temp : l) {
		System.out.println(temp.getEmail());
	}

}
*/


//	@Test
//	public void testAddClient() {
//		Client client = new Client();
//		client.setPrenom("jasser");
//		client.setNom("chaieb");
//		client.setPassword("123456");
//		try {
//			client.setDateNaissance(new SimpleDateFormat("dd/MM/yyyy").parse("23/09/1997"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		Client c1 = clientServiceImp.addClient(client);
//		id = c1.getIdClient();
//		assertNotNull(client);
//		testGetClientWith();
//		clientServiceImp.deleteClient(c1.getIdClient());
//		Client c = clientServiceImp.retrieveClient(c1.getIdClient());
//		assertNull(c);
//
//	}
	@Test
	public void testGetClientWith() {
		// TODO Auto-generated method stub
		User client = new User();
		client.setPrenom("jjjjjjj");
		client.setNom("ff");
		client.setPassword("123456");
		try {
			client.setDateNaissance(new SimpleDateFormat("dd/MM/yyyy").parse("23/09/1997"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User c1 = clientServiceImp.addUser(client);
		assertNotNull(c1);
		List<User> listClient = new ArrayList<User>();
		try {
			listClient = clientServiceImp.getClientWithDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1995"),
					new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(listClient.size()>0);

		clientServiceImp.deleteUser(c1.getIduser());
		User c = clientServiceImp.retrieveUser(c1.getIduser());
		assertNull(c);

	}

}
