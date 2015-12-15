package factory;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import data.Airport;
import data.Country;
import data.InputObject;
import reading_data.InputList;

public class MapFactoryTest 
{
	MapFactory mapFactory = new MapFactory();
	InputStream inStream = MapFactory.class.getClassLoader().getResourceAsStream("countries.csv");
	InputList inputList = mapFactory.readFile(inStream);
	String [] ctry = new String[]{
			"302672", "AD", "Andorra", "EU", "http://en.wikipedia.org/wiki/Andorra" , ""
		};
	InputObject inputObject = new Country(ctry);
	
	
	
	@Test
	public void testReadFileAndBuildMap() 
	{
		List<String[]> rows = inputList.getAllRows();
		
		//Chech the first row if it's correctly read
		assertEquals(ctry[0] , rows.get(0)[0]);
		assertEquals(ctry[1] , rows.get(0)[1]);
		assertEquals(ctry[2] , rows.get(0)[2]);
		assertEquals(ctry[3] , rows.get(0)[3]);
		assertEquals(ctry[4] , rows.get(0)[4]);
		assertEquals(ctry[5] , rows.get(0)[5]);
		
		
		//Chech the last row if it's correctly read
		ctry = new String[]{
				"302613", "ZZ", "Unknown or unassigned country", "AF", "http://en.wikipedia.org/wiki/Unknown_or_unassigned_country" , ""
			};
		int numOfRows = rows.size();
		assertEquals(ctry[0] , rows.get(numOfRows-1)[0]);
		assertEquals(ctry[1] , rows.get(numOfRows-1)[1]);
		assertEquals(ctry[2] , rows.get(numOfRows-1)[2]);
		assertEquals(ctry[3] , rows.get(numOfRows-1)[3]);
		assertEquals(ctry[4] , rows.get(numOfRows-1)[4]);
		assertEquals(ctry[5] , rows.get(numOfRows-1)[5]);
	}
	


	@Test
	public void testBuildMap() {
		mapFactory.buildMap(inputList , "country", "name");
		InputObjectMap inputObjectMap = mapFactory.getMapCtryNameToCountry();
		//check the size of the map from names as keys
		assertEquals(247 , inputObjectMap.getAllKeysFromMap().size());		
		
		
		String [] ctryLast = new String[]{
				"302613", "ZZ", "Unknown or unassigned country", "AF", 
				"http://en.wikipedia.org/wiki/Unknown_or_unassigned_country", ""
		};
		
		//check if the map maps the country name to object first country
		Country ctryObject = new Country(ctry);
		assertEquals(ctryObject , inputObjectMap.getObjFromMap(ctryObject.getCountryName()) );
		
		//check if the map has element with key the code of the last country
		ctryObject = new Country(ctryLast);
		assertEquals(ctryObject , inputObjectMap.getObjFromMap(ctryObject.getCountryName()) );
		
		mapFactory.buildMap(inputList ,"country", "code");
		inputObjectMap = mapFactory.getMapCtryCodeToCountry();
		//check the size of the map from code names as keys
		assertEquals(247 , inputObjectMap.getAllKeysFromMap().size());		
		
		//check if the map has element with key the code of the first country
		ctryObject = new Country(ctry);
		assertEquals(ctryObject , inputObjectMap.getObjFromMap(ctryObject.getCode()) );
		
		//check if the map has element with key the code of the last country
		ctryObject = new Country(ctryLast);
		assertEquals(ctryObject , inputObjectMap.getObjFromMap(ctryObject.getCode()) );
		
		
		inStream = MapFactory.class.getClassLoader().getResourceAsStream("airports.csv");
		inputList = mapFactory.readFile(inStream);
		mapFactory.buildMap(inputList, "airport", "");
		inputObjectMap = mapFactory.getMapAirportIdentToAirport();
		//check the size of the map from names as keys
		assertEquals(46505 , inputObjectMap.getAllKeysFromMap().size());		
		
		inStream = MapFactory.class.getClassLoader().getResourceAsStream("runways.csv");
		inputList = mapFactory.readFile(inStream);
		mapFactory.buildMap(inputList, "runway", "");
		inputObjectMap = mapFactory.getMapRunwayIdToRunway();
		//check the size of the map from names as keys
		assertEquals(39536 , inputObjectMap.getAllKeysFromMap().size());		
	}
	
	
	@Test
	public void testGetMapFromInput() 
	{
		//test if reading of files and populating maps passes
		MapFactory mapFactory = new MapFactory();
		assertEquals(true , mapFactory != null);
		
		mapFactory.populateMap();
		
		
		//check if the map is correctly populated from files
		InputObjectMap inputObjectMap = new InputObjectMap();
		inputObjectMap.putToInputMap(inputObject.getCode(), inputObject);
		
		
		
		//check if they have same values
		Set<String> keys2 =  mapFactory.getMapTest().getAllKeysFromMap();
		Iterator iterator2 = keys2.iterator(); 
		while (iterator2.hasNext())
		{
			String key = (String) iterator2.next();
			assertEquals(inputObjectMap.getObjFromMap(key), 
					mapFactory.getMapTest().getObjFromMap(key));
		}
		
		//test if country  maps to a airports correctly
		assertEquals(26 , mapFactory.getMapCtryIdToAirportId().get("RS").size()); 
		
		
		//test if airport maps to a runways correctly
		assertEquals(null , mapFactory.getMapAirportIdToRunwayId().get("OOA")); 
	}
}
