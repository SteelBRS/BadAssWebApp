package org.elite.java;

import org.elite.java.jdo.PMF;
import org.elite.java.jdo.PostalCodeAndArea;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Torben Vesterager
 */
@Controller
@SuppressWarnings("unused")
public class PostalController {

	private static final DecimalFormat POSTAL_CODE_FORMAT = new DecimalFormat("0000");

	private Map<String, String> postalCodeAndAreaMap = new TreeMap<String, String>();

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void load() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(PostalCodeAndArea.class);
			List<PostalCodeAndArea> postalCodeAndAreaDB = (List<PostalCodeAndArea>) query.execute();
			for (PostalCodeAndArea postalCodeAndArea : postalCodeAndAreaDB) {
				postalCodeAndAreaMap.put(POSTAL_CODE_FORMAT.format(postalCodeAndArea.getPostalCode()), postalCodeAndArea.getPostalArea());
			}
		} finally {
			pm.close();
		}
	}

	@RequestMapping(value = "/postalCodesAndAreas", method = RequestMethod.GET)
	public @ResponseBody Set<String> getPostalCodesAndAreas(@RequestParam String term) {

		Set<String> toReturn = new TreeSet<String>();
		for (String postalCode : postalCodeAndAreaMap.keySet())
			if (postalCode.startsWith(term))
				toReturn.add(postalCode + " " + postalCodeAndAreaMap.get(postalCode));

		return toReturn;
	}
}
