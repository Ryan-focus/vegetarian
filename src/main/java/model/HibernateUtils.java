package model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
public class HibernateUtils {

	private static SessionFactory sessionFactory = buildSessionFactory();
	private static SessionFactory buildSessionFactory() {
		try {

			// Hibernate 5.x 的寫法
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure().build();
			// 以下為Mapping檔的寫法
			 Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			// 
			// 共同部分
			SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
			System.out.println("SessionFactory Ready...(/vegetarian/src/main/java/main/QueryAllRestaurants.java)");
			return sessionFactory; 
			
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	// 外界呼叫此靜態方法來取的 SessionFactory物件
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void close() {
		getSessionFactory().close();
	}

}