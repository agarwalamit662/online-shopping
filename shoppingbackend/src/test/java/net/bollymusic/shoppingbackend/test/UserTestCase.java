package net.bollymusic.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.bollymusic.shoppingbackend.dao.UserDAO;
import net.bollymusic.shoppingbackend.dto.Address;
import net.bollymusic.shoppingbackend.dto.Cart;
import net.bollymusic.shoppingbackend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user= null;
	private Cart cart = null;
	private Address address = null;
	
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("net.bollymusic.shoppingbackend");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
		
	}
	
	/*@Test
	public void testAdd(){
		
		user = new User();
		user.setFirstName("HLKJ");
		user.setLastName("mmn");
		user.setContactNumber("768678677");
		user.setEmail("abc.gmail.com");
		user.setRole("USER");
		user.setPassword("123456");
		
		// add the user
		
		assertEquals("Failed to add user!",true,userDAO.addUser(user));
		 
		
		address = new Address();
		address.setAddressLineOne("ONE LINE");
		address.setAddressLineTwo("LINE TWO");
		address.setCity("MUMNBAO");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("3000012");
		address.setBilling(true);
		
		// link the user with the address using user id

		address.setUserId(user.getId());
		
		assertEquals("Failed to add address",true,userDAO.addAddress(address));

		if(user.getRole().equals("USER")){
			// create a cart for this user
			
			cart = new Cart();
			cart.setUser(user);
			//cart.setUserId(user.getId());
			
			//add the cart
			assertEquals("Failed to add cart!",true,userDAO.addCart(cart));
			
			// add a shipping address for this user
			address = new Address();
			address.setAddressLineOne("SHJIPPING ONE LINE");
			address.setAddressLineTwo("LINE TWO");
			address.setCity("MUMNBAO");
			address.setState("Maharashtra");
			address.setCountry("India");
			address.setPostalCode("3000012");
			address.setShipping(true);
			
			// llink it with the user
			
			address.setUserId(user.getId());
			
			assertEquals("Failed To add shipping address", true, userDAO.addAddress(address));
			
		}
		
		
	}*/
	
	/*@Test
	public void testAdd(){
		
		user = new User();
		user.setFirstName("HLKJ");
		user.setLastName("mmn");
		user.setContactNumber("768678677");
		user.setEmail("abc.gmail.com");
		user.setRole("USER");
		user.setPassword("123456");
		
		// add the user
		
		
		 
		
		if(user.getRole().equals("USER")){
			// create a cart for this user
			
			cart = new Cart();
			cart.setUser(user);
			//cart.setUserId(user.getId());
			// attach cart with the user
			user.setCart(cart);
			
			
		}
		// add the user
		assertEquals("Failed to add user!",true,userDAO.addUser(user));
	}*/
	
	/*@Test
	public void testUpdateCart(){
		
		user = userDAO.getByEmail("abc.gmail.com");
		
		cart = user.getCart();
		
		cart.setCartLines(20);
		
		assertEquals("Failed to update the cart!",true,userDAO.updateCart(cart));
		
	}*/
	
	
	/*@Test
	public void testAddAddress(){
		
		// we need to add an user
		
		user = new User();
		user.setFirstName("HLKJ");
		user.setLastName("mmn");
		user.setContactNumber("768678677");
		user.setEmail("abc.gmail.com");
		user.setRole("USER");
		user.setPassword("123456");
		
		// add the user
		
		assertEquals("Failed to add user!",true,userDAO.addUser(user));
		 
		// we are going to add the address
		
		address = new Address();
		address.setAddressLineOne("ONE LINE");
		address.setAddressLineTwo("LINE TWO");
		address.setCity("MUMNBAO");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("3000012");
		address.setBilling(true);
		
		//attach the user to the address
		address.setUser(user);

		assertEquals("Failed to add address", true,userDAO.addAddress(address));
		
		// we are also going to add the shipping address
		
		address = new Address();
		address.setAddressLineOne("SHIPPING ONE LINE");
		address.setAddressLineTwo("LINE TWO");
		address.setCity("MUMNBAO");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("3000012");
		address.setShipping(true);
		//attach the user
		address.setUser(user);

		assertEquals("Failed to add address", true,userDAO.addAddress(address));
		
		
	}*/
	
	
	/*@Test
	public void testAddAddress(){
		
		user = userDAO.getByEmail("abc.gmail.com");
		
		address = new Address();
		address.setAddressLineOne("New SHIPPING ONE LINE");
		address.setAddressLineTwo("LINE TWO");
		address.setCity("Bangalore");
		address.setState("Karnataka");
		address.setCountry("India");
		address.setPostalCode("3000012");
		address.setShipping(true);
		//attach the user
		address.setUser(user);

		assertEquals("Failed to add address", true,userDAO.addAddress(address));
		
	}*/
	
	@Test
	public void testAddresses(){
		
		user = userDAO.getByEmail("abc.gmail.com");
		
		assertEquals("Failed to fetch the list of addrss and size doesn't match!", 2,userDAO.listShippingAddress(user));
		
		assertEquals("Failed to fetch the billing addrss", "MUMNBAO",userDAO.getBillingAddress(user).getCity());
	}
	
}
