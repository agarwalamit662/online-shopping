package net.bollymusic.shoppingbackend.test;



import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.bollymusic.shoppingbackend.dao.CategoryDAO;
import net.bollymusic.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO  categoryDAO;
	private Category category;
	
	@BeforeClass
	public static void init(){
		
		context = new AnnotationConfigApplicationContext();
		context.scan("net.bollymusic.shoppingbackend");
		context.refresh();
		
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
		
		
	} 
	
	/*@Test
	public void testAddCategory(){
		
		category = new Category();
		category.setName("Laptop");
		category.setDescription("This is des for laptop");
		category.setImageURL("CAT_105.png");
		
		assertEquals("Successfully added a category into the category table",true,categoryDAO.add(category));
		
		
	}*/
	
	/*@Test
	public void testCategory(){
	
		category = categoryDAO.get(1);
		assertEquals("Successfully fetched a category from the category table","Television",category.getName());
	}*/
	
	/*@Test
	public void testUpdateCategory(){
	
		category = categoryDAO.get(1);
		
		category.setName("TV");
		
		assertEquals("Successfully Updated a category from the category table",true,categoryDAO.update(category));
	}*/
	
	/*@Test
	public void testDeleteCategory(){
	
		category = categoryDAO.get(1);
		assertEquals("Successfully Deleted a category from the category table",true,categoryDAO.delete(category));
	}*/
	
	/*@Test
	public void testAllActiveListCategory(){
	
		assertEquals("Successfully Fetched the list from the category table",1,categoryDAO.list().size());
	}*/
	
	@Test
	public void testCRUDCategory(){
		
		// add Operation
		category = new Category();
		category.setName("Laptop");
		category.setDescription("This is des for laptop");
		category.setImageURL("CAT_1.png");
		
		assertEquals("Successfully added a category into the category table",true,categoryDAO.add(category));
		
		category = new Category();
		category.setName("Television");
		category.setDescription("This is des for laptop");
		category.setImageURL("CAT_2.png");
		
		assertEquals("Successfully added a category into the category table",true,categoryDAO.add(category));
		
		category = categoryDAO.get(2);
		
		category.setName("TV");
		
		assertEquals("Successfully Updated a category from the category table",true,categoryDAO.update(category));

		// deleting the category
		
		//category = categoryDAO.get(2);
		assertEquals("Successfully Deleted a category from the category table",true,categoryDAO.delete(category));

		assertEquals("Successfully Fetched the list from the category table",1,categoryDAO.list().size());
		
	}
	
}
