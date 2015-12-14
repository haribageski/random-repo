package factory;

import java.io.IOException;
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

	private String _type;

	public InputObjectMap getMapFromInput(String type, String inFilePath)
	{
		this._type = type;
		return this.buildMap(type, this.readFile(inFilePath));
	}
	
	InputList readFile(String inFilePath)
	{
		InputList inputList = new InputList(inFilePath);
		
		try {
			inputList.readFromExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputList;
	}
	
	InputObjectMap buildMap(String type, InputList inList)
	{
		InputObjectMap inputMap = new InputObjectMap(type);
		List< List<String> > allRows = null;
		List<String> strList = null;	//used to store the string items from the input file
		List<Boolean> boolList = null;	//used to store the boolean items from the input file
		List<Double> doubleList = null;	//used to store the double items from the input file
		
		
		if(type.equals("country"))
		{
			for(int r = 1; r < allRows.size(); r++)
			{
				List<String> row = allRows.get(r);
				Country country = new Country(row);
				inputMap.putToInputMap(country.getCode() , country);
			}
		}
		
		else
		if(type.equals("airport"))
		{
			for(int r = 1; r < allRows.size(); r++)
			{
				List<String> row = allRows.get(r);
				Airport airport = new Airport(row);
				inputMap.putToInputMap(airport.getCode(), airport);
			}
		}
		else
		if(type.equals("runway"))
		{
			for(int r = 1; r < allRows.size(); r++)
			{
				List<String> row = allRows.get(r);
				
				Runway runway = new Runway(row);
				inputMap.putToInputMap(runway.getCode() , runway);
			}
		}
		return inputMap;
	}
}
