package com.dmoffat.chapter3;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dmoffat.Utils;

/**
 * Basic Hibernate example using annotated classes.
 * @author dan
 *
 */
public class EmployeeManager {
	private SessionFactory sessionFactory;
		
	public EmployeeManager() {
		sessionFactory = Utils.createSessionFactory();
	}
	
	public void add() {
		Session s = sessionFactory.getCurrentSession();
		Transaction t = s.beginTransaction();
		
		s.save(Utils.createEmployee());
		t.commit();
	}
	
	public void list() {
		Session s = sessionFactory.getCurrentSession();
		Transaction t = s.beginTransaction();
		
		List<Employee> employeees = s.createQuery("from Employee").list();
		
		System.out.println("Found " + employeees.size() + " employees. " + employeees);
	}
	
	public static void main(String[] args) {	
	
		EmployeeManager em = new EmployeeManager();
		
		em.add();
		em.list();
		
	}
}
