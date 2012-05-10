package org.elite.java;

import org.elite.java.jaxb.Persons;
import org.elite.java.jdo.PMF;
import org.elite.java.jdo.Person;
import org.elite.java.jdo.PostalCodeAndArea;
import org.junit.Test;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
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

		Properties postalCodeAndAreaProps = new Properties();
		postalCodeAndAreaProps.load(getClass().getResourceAsStream("/postnummerregister_ansi.properties"));

		Set<PostalCodeAndArea> postalCodeAndAreaSet = new HashSet<PostalCodeAndArea>();
		for (String postalCode : postalCodeAndAreaProps.stringPropertyNames())
			postalCodeAndAreaSet.add(new PostalCodeAndArea(Short.parseShort(postalCode), postalCodeAndAreaProps.getProperty(postalCode)));

		PersistenceManager pm = PMF.get().getPersistenceManager();

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

	@Test
	@SuppressWarnings("unchecked")
	public void initPerson() throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance("org.elite.java.jaxb");
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Persons persons = (Persons) unmarshaller.unmarshal(getClass().getResourceAsStream("/Persons.xml"));

		Set<Person> personSet = new HashSet<Person>();
		for(Persons.Person person : persons.getPerson())
			personSet.add(new Person(person.getName(), person.getHeight(), person.getWeight()));

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			// Delete existing
			Query query = pm.newQuery(Person.class);
			List<Person> personDB = (List<Person>) query.execute();
			pm.deletePersistentAll(personDB);

			// Insert all & check
			pm.makePersistentAll(personSet);
			personDB = (List<Person>) query.execute();
			assertEquals(persons.getPerson().size(), personDB.size());
		} finally {
			pm.close();
		}
	}
}
