package factory;

import static org.junit.Assert.*;

import org.junit.Test;

import data.Airport;
import data.Country;
import data.InputObject;
import data.Runway;

public class InputObjectMapTest {

	@Test
	public void testPutAndGetInputObjectMap() 
	{
		InputObjectMap objectMap = new InputObjectMap();
		assertEquals(null , objectMap.getObjFromMap("some country"));		
		
		InputObject inObj = new Country(new String[0]);
		objectMap.putToInputMap("some country", inObj);	
		assertEquals(inObj , objectMap.getObjFromMap("some country"));	
		
		inObj = new Runway(new String[0]);
		objectMap.putToInputMap("some country", inObj);	
		assertEquals(inObj , objectMap.getObjFromMap("some country"));	
		
		inObj = new Airport(new String[0]);
		objectMap.putToInputMap("some country", inObj);	
		assertEquals(inObj , objectMap.getObjFromMap("some country"));	
	}

}
