package net.bollymusic.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.bollymusic.onlineshopping.util.FileUploadUtility;
import net.bollymusic.onlineshopping.validator.ProductValidator;
import net.bollymusic.shoppingbackend.dao.CategoryDAO;
import net.bollymusic.shoppingbackend.dao.ProductDAO;
import net.bollymusic.shoppingbackend.dto.Category;
import net.bollymusic.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false)String operation){
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("userClickManageProducts", true);
		modelAndView.addObject("title","Manage Products");
		
		Product nProduct =  new Product();
		
		// set few of the  fields
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		modelAndView.addObject("product", nProduct);
		
		if(null != operation){
			if("product".equals(operation)){
				modelAndView.addObject("message", "Product Submitted Successfully");
			}
			else if("category".equals(operation)){
				modelAndView.addObject("message", "Category Submitted Successfully");
			}
		}
		
		return modelAndView;
	}
	
	
	// returing categories for all the request mappings
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		
		return categoryDAO.list();
		
	}
	
	// handling product Submission
	
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mproduct,BindingResult results,Model model,HttpServletRequest httpServletRequest){
		
		
		if(mproduct.getId() == 0){
			new ProductValidator().validate(mproduct, results);
		}
		else{
			if(!mproduct.getFile().getOriginalFilename().equals("")){
				new ProductValidator().validate(mproduct, results);
			}
		}
		/// check if there are any errors
		if(results.hasErrors()){
			
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title","Manage Products");
			model.addAttribute("message", "Validation Failed For Product Submission");
			
			return "page";
		}
		
		logger.info(mproduct.toString());
		
		if(mproduct.getId() == 0){
			productDAO.add(mproduct);
		}
		else{
			// update the product if id not zero
			productDAO.update(mproduct);
		}
		if(!mproduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(httpServletRequest,mproduct.getFile(),mproduct.getCode());
		}
		
		
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value="/product/{id}/activation",method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id){
		// fetch from database
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		product.setActive(!isActive);
		// activating and deactivating based on the value of active field
		productDAO.update(product);
		return (isActive)?"You have successfully deactivated the product with id "+product.getId()
		:"You have successfully activated  the product with id: "+product.getId();
	}
	
	@RequestMapping(value="/{id}/product",method=RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id){
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("userClickManageProducts", true);
		modelAndView.addObject("title","Manage Products");
		
		Product nProduct =  productDAO.get(id);
		//set the product fetched from databasse
		modelAndView.addObject("product", nProduct);
		
		return modelAndView;
	}
	
	
	@ModelAttribute("category")
	public Category getCategory(){
		return new Category();
	}
	
	// to handle category submission
	@RequestMapping(value="/category",method=RequestMethod.POST)
	public String hanldeCategorySubmission(@ModelAttribute Category category){
		// add the new category
		
		categoryDAO.add(category);
		
		return "redirect:/manage/products?operation=category";
		
	}
	
	
}


