package net.bollymusic.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import net.bollymusic.onlineshopping.model.RegisterModel;
import net.bollymusic.shoppingbackend.dao.UserDAO;
import net.bollymusic.shoppingbackend.dto.Address;
import net.bollymusic.shoppingbackend.dto.Cart;
import net.bollymusic.shoppingbackend.dto.User;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDAO;
	
	public RegisterModel init() {
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel,User user){
	
		registerModel.setUser(user);
			
	}
	
	public void addBilling(RegisterModel registerModel,Address billing){
		
		registerModel.setBilling(billing);
			
	}
	
	public String validateUser(User user,MessageContext messageContext){
		
		String transitionValue = "success";
		if(!user.getPassword().equals(user.getConfirmPassword())){
			transitionValue = "failure";
			messageContext.addMessage(new MessageBuilder().
					error().source("confirmPassword").
					defaultText("Password doesn't match the confirm password!")
					.build());
		}
		
		// check the uniqueness of the email id
		
		if(userDAO.getByEmail(user.getEmail())!=null){
			transitionValue = "failure";
			messageContext.addMessage(new MessageBuilder().
					error().source("email").
					defaultText("Email Id is already in Use!")
					.build());
		}
		
		return transitionValue;
		
	}
	
	public String saveAll(RegisterModel model) {
		
		User user = model.getUser();
		if(user.getRole().equals("USER")){
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		// save the user
		userDAO.addUser(user);
		
		Address billing = model.getBilling();
		billing.setUser(user);
		billing.setBilling(true);
		userDAO.addAddress(billing);
		
		String transitionValue="success";
		return transitionValue;
	}
	
}
