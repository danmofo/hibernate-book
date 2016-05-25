package com.dmoffat;

import java.io.File;
import java.util.Random;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.dmoffat.chapter3.Employee;
import com.dmoffat.chapter4.Car;
import com.dmoffat.chapter4.Showroom;

public class Utils {
		
	public static SessionFactory createSessionFactory() {
		ClassLoader loader = Utils.class.getClassLoader();
		File configFile = new File(loader.getResource("com/dmoffat/chapter1/hibernate.cfg.xml").getFile());
		
		Configuration config = new Configuration()
								.configure(configFile)
								.addAnnotatedClass(Employee.class)
								.addAnnotatedClass(Car.class)
								.addAnnotatedClass(Showroom.class);
		
		ServiceRegistry registry = new ServiceRegistryBuilder()
										.applySettings(config.getProperties())
										.buildServiceRegistry();
		
		return config.buildSessionFactory(registry);
	}
	
	public static Employee createEmployee() {
		Employee e = new Employee();
		e.setId(new Random().nextInt(1000));
		e.setName("Pledge Smith");
		return e;
	}
	
	public static Car createCar() {
		Car c = new Car();
		c.setColor("Color " + new Random().nextInt(1000));
		c.setName("Car " + new Random().nextInt(1000));
		
		return c;
	}
}
