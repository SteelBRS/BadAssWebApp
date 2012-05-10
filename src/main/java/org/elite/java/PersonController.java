package org.elite.java;

import org.elite.java.jdo.PMF;
import org.elite.java.jdo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Torben Vesterager
 */
@Controller
@SuppressWarnings("unused")
public class PersonController {

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	@SuppressWarnings("unchecked")
	public @ResponseBody List<Person> getPersons() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(Person.class);
			return (List<Person>) query.execute();
		} finally {
			pm.close();
		}
	}
}
