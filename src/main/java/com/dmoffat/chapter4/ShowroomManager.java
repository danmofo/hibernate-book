package com.dmoffat.chapter4;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dmoffat.Utils;


/**
 * One-to-many example.
 * @author dan
 *
 */
public class ShowroomManager {
	
	SessionFactory sessionFactory;
	
	public ShowroomManager() {
		sessionFactory = Utils.createSessionFactory();
	}
	
	private void create() {
		Session s = sessionFactory.getCurrentSession();
		Transaction t = s.beginTransaction();
		
		Showroom sr = new Showroom();
		Car c = Utils.createCar();
		c.setShowroom(sr);

		
		List<Car> cars = new ArrayList<Car>();
		cars.add(c);
		
		
		sr.setLocation("Evesham");
		sr.setManager("Daniel Smith");
		sr.setCars(cars);
		
		System.out.println("Creating " + sr);
		
		s.save(sr);
		
		t.commit();
	}
	
	private void getById(int id) {
		Session s = sessionFactory.getCurrentSession();
		Transaction t = s.beginTransaction();
		
		Showroom sr = (Showroom)s.load(Showroom.class, id);
		
		System.out.println("Found " + sr);
	}
	
	
	
	public static void main(String[] args) {
		
		ShowroomManager sm = new ShowroomManager();
		
		sm.create();
		sm.getById(1);
	}

}
