package factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import data.Airport;
import data.Country;
import data.Runway;
import reading_data.InputList;

public class MapFactory {

	private String _type = "";
	private InputStream inStream = null;
	private InputObjectMap _inObjMapInFactory = null;
	
	public MapFactory(String type)
	{
		this._type = type;
	}
	
	private void setInObjMapInFactory(InputObjectMap inObjMapInFactory)
	{
		this._inObjMapInFactory = inObjMapInFactory;
	}
	
	private InputObjectMap getInObjMapInFactory( )
	{
		return this._inObjMapInFactory;
	}

	/**
	 * If first initiates an InputStream to the corresponding file with regards to the 
	 * type (country,airport, or runway).
	 * Then it calls readFile to initialize InputList.
	 * Lastly, buildMap() is called to initialize InputObjectMap from the InputList in the 
	 * previous step.
	 * 
	 * @param none
	 * @return InputObjectMap
	 */
	public InputObjectMap getMapFromInput()
	{	
		if(this._type.equals("country"))
		{
			inStream =  MapFactory.class.getClassLoader().getResourceAsStream("countries.csv");
		}
		else
			if(this._type.equals("airport"))	
				inStream = MapFactory.class.getClassLoader().getResourceAsStream("airports.csv");
		else
			if(this._type.equals("runway"))	
				inStream = MapFactory.class.getClassLoader().getResourceAsStream("runways.csv");
		else
			if(MapFactory.class.getClassLoader().getResourceAsStream(this._type + ".csv") != null)	
				inStream = MapFactory.class.getClassLoader().getResourceAsStream(this._type + ".csv");
		else
			return null;
		
		if(inStream == null)
		{
			System.out.println("inStream == null");
			return null;
		}
		InputList inList = this.readFile(inStream);
		this.setInObjMapInFactory( this.buildMap(inList));
		return this.getInObjMapInFactory();
	}
	
	/**
	 * We define InputList with the parameter inStream. 
	 * Then we populate it with the file by calling readCSV().
	 * 
	 * @param inStream
	 * @return
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
	 * @param inList
	 * @return
	 */
	InputObjectMap buildMap(InputList inList)
	{
		InputObjectMap inputMap = new InputObjectMap();
		List< String[] > allRows = inList.getAllRows();
		
		
		if(this._type.equals("country") || this._type.equals("test_country"))
		{
			for(int r = 0; r < allRows.size(); r++)
			{
				String[] row = allRows.get(r);
				
				if(row.length == 0 || row == null)		//no need to create empty Contries
					continue;
				
				Country country = new Country(row);
				if(country.getCode().equals(""))
					continue;
				
				if(inputMap.getAllKeysFromMap().contains(country.getCode()))
				{
					System.out.println(country.getCode()  + " already stored in map");
				}
				
				inputMap.putToInputMap(country.getCode() , country);
			}
		}
		
		else
		if(this._type.equals("airport"))
		{
			for(int r = 0; r < allRows.size(); r++)
			{
				String[] row = allRows.get(r);
				
				if(row.length == 0 || row == null)		//no need to create empty Airports
					continue;
				
				Airport airport = new Airport(row);
				inputMap.putToInputMap(airport.getCode(), airport);
			}
		}
		else
		if(this._type.equals("runway"))
		{
			for(int r = 0; r < allRows.size(); r++)
			{
				String[] row = allRows.get(r);
				
				if(row.length == 0 || row == null)		//no need to create empty Runways
					continue;
				
				Runway runway = new Runway(row);
				inputMap.putToInputMap(runway.getCode() , runway);
			}
		}
		return inputMap;
	}
}
