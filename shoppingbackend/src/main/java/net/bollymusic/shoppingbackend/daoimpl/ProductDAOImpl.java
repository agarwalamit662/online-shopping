package net.bollymusic.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bollymusic.shoppingbackend.dao.ProductDAO;
import net.bollymusic.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product get(int productId) {
		return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
	}

	@Override
	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	@Override
	public boolean add(Product Product) {
		try {
			sessionFactory.getCurrentSession().persist(Product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Product Product) {
		try {
			sessionFactory.getCurrentSession().update(Product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Product Product) {
		Product.setActive(false);
		try{
			sessionFactory.getCurrentSession().update(Product);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Product> listActiveProducts() {
		String query  = "FROM Product where active=:active";
		return sessionFactory.getCurrentSession().
				createQuery(query,Product.class).
				setParameter("active", true).
				getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String query  = "FROM Product where active=:active and categoryId=:categoryId";
		return sessionFactory.getCurrentSession().
				createQuery(query,Product.class).
				setParameter("active", true).
				setParameter("categoryId",categoryId ).
				getResultList();
		
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		
		return sessionFactory.getCurrentSession().
				createQuery("FROM Product WHERE active =:active ORDER BY id ",Product.class).
				setParameter("active", true).
				setFirstResult(0).
				setMaxResults(count).
				getResultList();
	}

}
