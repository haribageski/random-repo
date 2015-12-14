package factory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.sun.org.apache.xerces.internal.xs.StringList;

import data.Airport;
import data.Country;
import data.InputObject;
import data.Runway;
import reading_data.InputList;

public class InputMapFactory {

	private String _type = "";
	private InputStream inStream = null;
	
	public InputMapFactory(String type)
	{
		this._type = type;
		System.out.println("Input type in InputMapFactory is:" + type);
	}

	public InputObjectMap getMapFromInput()
	{	
		if(this._type.equals("country"))
		{
			inStream =  InputMapFactory.class.getClassLoader().getResourceAsStream("countries.csv");
			if(inStream == null)
			{
				System.out.println("inStream == null");
				return null;
			}
		}
		else
			if(this._type.equals("airport"))	
				inStream = InputMapFactory.class.getClassLoader().getResourceAsStream("airports.csv");
		else
			if(this._type.equals("runway"))	
				inStream = InputMapFactory.class.getClassLoader().getResourceAsStream("runways.csv");
		else
			return null;
		
		InputList inList = this.readFile(inStream);
		return this.buildMap(inList);
	}
	
	InputList readFile(InputStream inStream)
	{
		if(inStream == null)
		{
			System.out.println("inStream in InputMapFactory is null");
			return null;
		}
		InputList inputList = new InputList(inStream);
		System.out.println("inputList from input file is  nule:" + inputList.equals(null));
		
		try {
			inputList.readCSV();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputList;
	}
	
	InputObjectMap buildMap(InputList inList)
	{
		InputObjectMap inputMap = new InputObjectMap(this._type);
		List< String[] > allRows = inList.getAllRows();
		
		if(this._type.equals("country"))
		{
			for(int r = 1; r < allRows.size(); r++)
			{
				String[] row = allRows.get(r);
				System.out.println("Row size to pass to Country:" + row.length);
				
				if(row.length == 0 || row == null)		//no need to create empty Contries
					continue;
				
				Country country = new Country(row);
				inputMap.putToInputMap(country.getCode() , country);
			}
		}
		
		else
		if(this._type.equals("airport"))
		{
			for(int r = 1; r < allRows.size(); r++)
			{
				String[] row = allRows.get(r);
				System.out.println("Row size to pass to Airport:" + row.length);
				
				if(row.length == 0 || row == null)		//no need to create empty Airports
					continue;
				
				Airport airport = new Airport(row);
				inputMap.putToInputMap(airport.getCode(), airport);
			}
		}
		else
		if(this._type.equals("runway"))
		{
			for(int r = 1; r < allRows.size(); r++)
			{
				String[] row = allRows.get(r);
				System.out.println("Row size to pass to Runway:" + row.length);
				
				if(row.length == 0 || row == null)		//no need to create empty Runways
					continue;
				
				Runway runway = new Runway(row);
				inputMap.putToInputMap(runway.getCode() , runway);
			}
		}
		return inputMap;
	}
}
