package data;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountryTest {

	@Test
	public void testGetCode() {
		Country country = new Country(new String[0]);
		assertEquals("" , country.getCode());		//code of initialized empty object
		
		
		String[] countryParams = new String[]{
				"302618",	"AE", "United Arab Emirates", "AS", 
				"http://en.wikipedia.org/wiki/United_Arab_Emirates", "UAE"
			};
		country= new Country(countryParams);
		assertEquals("AE" , country.getCode());		//code of initialized empty object
	}		
	
	@Test
	public void testGetCountryName() {
		Country country = new Country(new String[0]);
		assertEquals("" , country.getCountryName());		//code of initialized empty object
		
		
		String[] countryParams = new String[]{
				"302618",	"AE", "United Arab Emirates", "AS", 
				"http://en.wikipedia.org/wiki/United_Arab_Emirates", "UAE"
			};
		country= new Country(countryParams);
		assertEquals("United Arab Emirates" , country.getCountryName());		//code of initialized empty object
	}

}

