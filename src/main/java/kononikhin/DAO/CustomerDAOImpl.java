package kononikhin.DAO;

import kononikhin.Entityes.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        Session session = sessionFactory.getCurrentSession();

        Query<Customer> query = session.createQuery("from Customer order by firstName,lastName", Customer.class);

        return query.list();
    }

    @Override
    public void save(Customer customer) {

        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(customer);

    }

    @Override
    public Customer getCustomer(int customerId) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Customer.class, customerId);
    }

    @Override
    public void deleteCustomer(int customerId) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete  from Customer where id=:customerId");

        query.setParameter("customerId", customerId);

        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String searchName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query query = null;

        // only search by name if theSearchName is not empty
        if (searchName != null && searchName.trim().length() > 0) {

            // search for firstName or lastName
            query = currentSession.createQuery("from Customer where lower(firstName) like :name or lower(lastName) like :name", Customer.class);
            query.setParameter("name", "%" + searchName.toLowerCase() + "%");

        } else {
            // theSearchName is empty
            query = currentSession.createQuery("from Customer", Customer.class);
        }

        // execute query and get result list
        List<Customer> customers = query.getResultList();

        // return the results
        return customers;
    }
}
