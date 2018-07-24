package com.capg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.capg.mypaymentapp.beans.Customer;
import com.capg.mypaymentapp.beans.Transaction;

public class WalletRepoImpl implements WalletRepo{

	private static EntityManagerFactory factory;
	
	
	public WalletRepoImpl() {
		factory = Persistence.createEntityManagerFactory("JPA-PU");	
	}
	public boolean save(Customer customer) 
	{
	
			EntityManager entityManager = factory.createEntityManager();
			EntityManager entityManager1 = 
					factory.createEntityManager();
		if(Thread.currentThread().getStackTrace()[2].getMethodName().equals("createAccount"))
		{
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
		return true;
		}
		else
		{
	entityManager1.getTransaction().begin();
	entityManager1.find(Customer.class,customer.getMobileNo()).getWallet().setBalance(new BigDecimal(0));
    entityManager1.getTransaction().commit();
			return false;
		}
	}
	public Customer findOne(String mobileNo) 
	{
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Customer c=entityManager.find(Customer.class,mobileNo);
		entityManager.getTransaction().commit();
		return c;
		
	}
	public void saveTransaction(String mobileNo,String s) 
	{

		EntityManager entityManager4 = null;
		if(entityManager4==null || !entityManager4.isOpen()) {
			entityManager4 = factory.createEntityManager();
	}
		Transaction t=new Transaction(mobileNo,s);
		entityManager4.getTransaction().begin();
		entityManager4.persist(t);
		entityManager4.getTransaction().commit();	
	}

	public List<?> getTransaction(String mobileNo) {	
		EntityManager entityManager= null;
		if(entityManager==null || !entityManager.isOpen()) {
			entityManager = factory.createEntityManager();
	}
		entityManager.getTransaction().begin();
		Query q=entityManager.createQuery("select t.transactionLog from Transaction t  where t.mobileNo=:mobileno",String.class);
		q.setParameter("mobileno", mobileNo);
		List l=q.getResultList();
		entityManager.getTransaction().commit();
		return l;
}
	}

