package com.dmoffat.chapter1;

import java.io.File;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.dmoffat.chapter1.model.Movie;

/**
 * A very basic example of using Hibernate.
 * @author danielmoffat
 *
 */
public class BasicMovieManager {
	private SessionFactory sessionFactory;
	
	private void initSessionFactory() {
		ClassLoader loader = BasicMovieManager.class.getClassLoader();
		File configFile = new File(loader.getResource("com/dmoffat/chapter1/hibernate.cfg.xml").getFile());
		
		Configuration config = new Configuration().configure(configFile);
		
		ServiceRegistry registry = new ServiceRegistryBuilder()
										.applySettings(config.getProperties())
										.buildServiceRegistry();
		
		sessionFactory = config.buildSessionFactory(registry);
	}
	
	public BasicMovieManager() {
		initSessionFactory();
	}
	
	private void persistMovie() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Movie movie = new Movie();
		movie.setDirector("Daniel Moffat");
		movie.setSynopsis("Nothing interesting happens.");
		movie.setTitle("Average");
		
		session.save(movie);
		session.getTransaction().commit();
	}
	
	private void findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Movie movie = (Movie)session.load(Movie.class, id); 
		System.out.println("Found: " + movie);
		
		session.getTransaction().commit();
	}
	
	private void findAll() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		List<Movie> movies = session.createQuery("from Movie").list();
		System.out.println("Found " + movies.size() + " movies!");
		
		session.getTransaction().commit();
	}
	
	public static void main(String[] args) {	
		BasicMovieManager manager = new BasicMovieManager();
		
		manager.persistMovie();
		manager.findById(2);
		manager.findAll();
	}
}