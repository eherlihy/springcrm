package springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		// get the current Hibernate session
		Session session = sessionFactory.getCurrentSession();
		// create a query, sort by last name
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
		// execute query and get results list
		List<Customer> customers = query.getResultList();
		// return results
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		// get the current Hibernate session
		Session session = sessionFactory.getCurrentSession();
		// save the customer (or update if already exists)
		session.saveOrUpdate(customer);
		return;
	}

	@Override
	public Customer getCustomer(int theId) {
		// get the current Hibernate session
		Session session = sessionFactory.getCurrentSession();
		// get customer from ID
		Customer customer = session.get(Customer.class, theId);
		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
		// get the current Hibernate session
		Session session = sessionFactory.getCurrentSession();
		// get customer from ID
		Customer customer = session.get(Customer.class, theId);
		// delete customer
		session.delete(customer);
	}

	@Override
	public List<Customer> searchCustomer(String theSearch) {
		// get the current Hibernate session
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		// if theSearch is not empty...
		if (theSearch != null && (theSearch.trim().length() > 0)) {
			// search for first name or last name
			query = session.createQuery("from Customer where lower(firstName) like:search or lower(lastName) like:search", Customer.class);
			query.setParameter("search", "%" + theSearch.toLowerCase() + "%");
		}
		else {
			// get all customers
			query = session.createQuery("from Customer", Customer.class);
		}
		// execute query
		List<Customer> customers = query.getResultList();
		return customers;
	}
}
