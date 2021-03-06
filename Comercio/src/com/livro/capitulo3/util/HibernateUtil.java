package com.livro.capitulo3.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	private static final SessionFactory sessionFactory = buSessionFactory();
	private static SessionFactory buSessionFactory(){
		try {
		Configuration cfg = new AnnotationConfiguration() ;
		cfg.configure("hibernate.cfg.xml");
		return cfg.buildSessionFactory();
		}
		catch(Throwable e)
		{
		 System.out.println("Criação inicial do objeto SessionFactory falhou. Erro:" + e);	
		 throw new ExceptionInInitializerError(e);
		}
	}
		
	
	public static SessionFactory getSessionFactory() {
			return sessionFactory ;
			
		}
		
	}


