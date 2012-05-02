package org.elite.java;

import org.elite.java.jdo.PMF;
import org.elite.java.jdo.PostalCodeAndArea;
import org.junit.Test;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Torben Vesterager
 */
public class InitDBTest {

	@Test
	@SuppressWarnings("unchecked")
	public void initPostalCodeAndArea() throws Exception {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Properties postalCodeAndAreaProps = new Properties();
		postalCodeAndAreaProps.load(getClass().getResourceAsStream("/postnummerregister_ansi.properties"));

		Set<PostalCodeAndArea> postalCodeAndAreaSet = new HashSet<PostalCodeAndArea>();
		for (String postalCode : postalCodeAndAreaProps.stringPropertyNames())
			postalCodeAndAreaSet.add(new PostalCodeAndArea(Short.parseShort(postalCode), postalCodeAndAreaProps.getProperty(postalCode)));

		try {
			// Delete existing
			Query query = pm.newQuery(PostalCodeAndArea.class);
			List<PostalCodeAndArea> postalCodeAndAreaDB = (List<PostalCodeAndArea>) query.execute();
			pm.deletePersistentAll(postalCodeAndAreaDB);

			// Insert all & check
			pm.makePersistentAll(postalCodeAndAreaSet);
			postalCodeAndAreaDB = (List<PostalCodeAndArea>) query.execute();
			assertEquals(postalCodeAndAreaProps.size(), postalCodeAndAreaDB.size());
		} finally {
			pm.close();
		}
	}
}
