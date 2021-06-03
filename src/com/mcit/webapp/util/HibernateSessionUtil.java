package com.mcit.webapp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mcit.webapp.entity.Employee;
import com.mcit.webapp.entity.Payroll;
import com.mcit.webapp.entity.Project;

public class HibernateSessionUtil {
	
	private static SessionFactory factory;
	
	public static SessionFactory buildSessionFactory() {
		
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Payroll.class)
				.addAnnotatedClass(Project.class)
				.buildSessionFactory();
		return factory;
	}

}
