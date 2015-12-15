package factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import data.Airport;
import data.Country;
import data.Runway;
import reading_data.InputList;

public class MapFactory 
{

	private InputStream inStream = null;
	
	private InputObjectMap mapCtryNameToCountry = null;
	private InputObjectMap mapCtryCodeToCountry = null;
	private HashMap<String, ArrayList<String> > mapCtryIdToAirportId = null;
	private InputObjectMap mapAirportIdentToAirport = null;
	private HashMap<String, ArrayList<String> > mapAirportIdToRunwayId = null;
	private InputObjectMap mapRunwayIdToRunway = null;
	private InputObjectMap mapTest = null;
	
	public MapFactory()
	{
		mapCtryNameToCountry = new InputObjectMap();
		mapCtryCodeToCountry = new InputObjectMap();
		mapCtryIdToAirportId = new HashMap<String, ArrayList<String> >();
		mapAirportIdentToAirport = new InputObjectMap();
		mapAirportIdToRunwayId = new HashMap<String, ArrayList<String> >();
		mapRunwayIdToRunway = new InputObjectMap();
		mapTest = new InputObjectMap();
	}
	
	public InputObjectMap getMapCtryNameToCountry()
	{
		return this.mapCtryNameToCountry;
	}
	public InputObjectMap getMapCtryCodeToCountry( )
	{
		return this.mapCtryCodeToCountry;
	}
	public HashMap<String, ArrayList<String> >  getMapAirportIdToRunwayId()
	{
		return this.mapAirportIdToRunwayId;
	}
	
	public HashMap<String, ArrayList<String>> getMapCtryIdToAirportId( )
	{
		return this.mapCtryIdToAirportId;
	}
	
	public InputObjectMap getMapAirportIdentToAirport( )
	{
		return this.mapAirportIdentToAirport;
	}
	public InputObjectMap getMapRunwayIdToRunway( )
	{
		return this.mapRunwayIdToRunway;
	}
	public InputObjectMap getMapTest( )
	{
		return this.mapTest;
	}
	
	/**
	 * Populates all maps from the three files by calling populateMapOfGivenType() with type 
	 * "country", "airport", "runway".
	 */
	public void populateMap()
	{
		/*It is very important to do the reading of files in the following order,
		 * otherwise we will get an error
		 */
		this.populateMapOfGivenType("country");
		this.populateMapOfGivenType("airport");
		this.populateMapOfGivenType("runway");
	}
	
	
	/**
	 * If first initiates an InputStream to the corresponding file with regards to the 
	 * type (country,airport, or runway).
	 * Then it calls readFile to initialize InputList.
	 * Lastly, buildMap() is called to initialize InputObjectMap from the InputList in the 
	 * previous step.
	 * 
	 * @param String type
	 * @return none
	 */
	public void populateMapOfGivenType(String type)
	{	
		if(type.equals("country"))
		{
			inStream =  MapFactory.class.getClassLoader().getResourceAsStream("countries.csv");
		}
		else
			if(type.equals("airport"))	
				inStream = MapFactory.class.getClassLoader().getResourceAsStream("airports.csv");
		else
			if(type.equals("runway"))	
				inStream = MapFactory.class.getClassLoader().getResourceAsStream("runways.csv");
		else
			if(MapFactory.class.getClassLoader().getResourceAsStream(type + ".csv") != null)	
				inStream = MapFactory.class.getClassLoader().getResourceAsStream(type + ".csv");
		else
			return;
		
		if(inStream == null)
		{
			System.out.println("inStream == null");
			return;
		}
		InputList inList = this.readFile(inStream);
		
		String keyType = "name";
		this.buildMap(inList , type, keyType);
		if(type.equals("country"))
		{
			keyType = "code";
			this.buildMap(inList , type, keyType);
		}
	}
	
	/**
	 * We define InputList with the parameter inStream. 
	 * Then we populate it with the file by calling readCSV().
	 * 
	 * @param InputStream
	 * @return InputList
	 */
	InputList readFile(InputStream inStream)
	{
		if(inStream == null)
		{
			System.out.println("inStream in InputMapFactory is null");
			return null;
		}
		InputList inputList = new InputList(inStream);
		
		try {
			inputList.readCSV();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputList;
	}
	
	/**
	 * It first defines Country, Runway, or Airport, and then inserts it to an link InputObjectMap 
	 * as a value with key its codeName.
	 * @param InputList inList, String keyType
	 * @return InputObjectMap
	 */
	void buildMap(InputList inList, String objectType, String keyType)
	{
		List< String[] > allRows = inList.getAllRows();
		
		
		if(objectType.equals("country") || objectType.equals("test_country"))
		{
			for(int r = 0; r < allRows.size(); r++)
			{
				String[] row = allRows.get(r);
				
				if(row.length == 0 || row == null)		//no need to create empty Contries
					continue;
				
				Country country = new Country(row);
				if(country.getCode().equals(""))
					continue;
				
				if(this.getMapCtryCodeToCountry().getAllKeysFromMap().contains(country.getCode()))
				{
					System.out.println(country.getCode()  + " already stored in map");
				}
				
				if(keyType.equals("code"))
					mapCtryCodeToCountry.putToInputMap(country.getCode() , country);
				else
					if(keyType.equals("name"))
						mapCtryNameToCountry.putToInputMap(country.getCountryName() , country);
			}
		}
		
		else
		if(objectType.equals("airport"))
		{
			for(int r = 0; r < allRows.size(); r++)
			{
				String[] row = allRows.get(r);
				
				if(row.length == 0 || row == null)		//no need to create empty Airports
					continue;
				
				Airport airport = new Airport(row); 
				
				ArrayList<String> airportIds = new ArrayList<String>();
				if(airport.getCountryCode() != null && this.mapCtryIdToAirportId.containsKey(airport.getCountryCode()))
					airportIds = this.mapCtryIdToAirportId.get(airport.getCountryCode());
				airportIds.add(airport.getCode());
				this.mapCtryIdToAirportId.put(airport.getCountryCode(), airportIds);
				
				this.mapAirportIdentToAirport.putToInputMap(airport.getCode(), airport);
			}
		}
		else
		if(objectType.equals("runway"))
		{
			for(int r = 0; r < allRows.size(); r++)
			{
				String[] row = allRows.get(r);
				
				if(row.length == 0 || row == null)		//no need to create empty Runways
					continue;
				
				Runway runway = new Runway(row);
				
				ArrayList<String> runwayIds = new ArrayList<String>();
				if(runway.gerAirportId() != null && this.mapAirportIdToRunwayId.containsKey(runway.gerAirportId()))
					runwayIds = this.mapAirportIdToRunwayId.get(runway.gerAirportId());
				runwayIds.add(runway.getCode());
				this.mapAirportIdToRunwayId.put(runway.gerAirportId(), runwayIds);
				
				this.mapRunwayIdToRunway.putToInputMap(runway.getCode(), runway);
			}
		}
		else
		{
			for(int r = 0; r < allRows.size(); r++)
			{
				String[] row = allRows.get(r);
				
				if(row.length == 0 || row == null)		//no need to create empty Runways
					continue;
				
				Country country = new Country(row);
				this.mapTest.putToInputMap(country.getCode(), country);
			}
		}
			
	}
}
