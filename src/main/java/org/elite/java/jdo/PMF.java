package org.elite.java.jdo;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Torben Vesterager
 */
public class PMF {

	private static final PersistenceManagerFactory pmfInstance;

	static {
		Properties properties = new Properties();
		properties.setProperty("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
		properties.setProperty("javax.jdo.option.ConnectionDriverName", "com.mysql.jdbc.Driver");
		properties.setProperty("javax.jdo.option.ConnectionURL", "jdbc:mysql://localhost/test?useServerPrepStmts=false");
		properties.setProperty("javax.jdo.option.ConnectionUserName", "root");
		properties.setProperty("javax.jdo.option.ConnectionPassword", "password");
		properties.setProperty("datanucleus.autoCreateSchema", "true");
		pmfInstance = JDOHelper.getPersistenceManagerFactory(properties);
	}

	private PMF() {
	}

	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
}
