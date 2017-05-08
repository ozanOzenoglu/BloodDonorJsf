package org.kanbankasi.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = null;

	@SuppressWarnings("deprecation")
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {

			// TO-DO: Yeni versiyonda bu fonksiyonlar kullanýlabilir hale gelebilir, kontrol et
			//ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().configure().buildServiceRegistry();
			//sessionFactory = new Configuration().configure().buildSessionFactory(serviceRegistry);
			
			// TO-DO: Hibernate Core 4.1 yayýnlandiginda bu koda gecicez:
			/*ServiceRegistry sreg = new ServiceRegistryBuilder().configure().buildServiceRegistry();
			sessionFactory = new MetadataSources(sreg)
			.addResource("hibernate.cfg.xml")
			.buildMetadata()
			.buildSessionFactory();*/
			
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}
	
	public static Session openSession() {
		return getSessionFactory().openSession();
	}
}
