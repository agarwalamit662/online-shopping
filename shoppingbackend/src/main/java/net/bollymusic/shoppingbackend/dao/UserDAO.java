package net.bollymusic.shoppingbackend.dao;

import java.util.List;

import net.bollymusic.shoppingbackend.dto.Address;
import net.bollymusic.shoppingbackend.dto.Cart;
import net.bollymusic.shoppingbackend.dto.User;

public interface UserDAO {

	boolean addUser(User user);
	
	User getByEmail(String email);
	
	boolean addAddress(Address address);
	
	// update the cart
	boolean updateCart(Cart cart);
	
	Address getBillingAddress(User user);
	List<Address> listShippingAddress(User user);
	
	
}
