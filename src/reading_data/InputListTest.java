package reading_data;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.*;

import mapBuilder.MapBuilder;

public class InputListTest {

	@Test
	public void testGetAllRows() throws IOException 
	{
		InputList inputList = new InputList();
		
		assertEquals(null , inputList.getAllRows());			// _allRows not initialized
		
		InputStream inStream = MapBuilder.class.getClassLoader().getResourceAsStream("airports.csv");
		inputList = new InputList(inStream);
		assertEquals(true , inputList.getAllRows().isEmpty());	// _allRows initialized but empty
		inputList.readCSV();
		assertEquals(46505 , inputList.getAllRows().size());		// _allRows populated with all the rows
		assertEquals(18 , inputList.getAllRows().get(0).length);// _allRows populated with all the columns
	
		
	}

	@Test
	public void testReadCSV() throws IOException 
	{
		InputStream inStream = MapBuilder.class.getClassLoader().getResourceAsStream("wrong_filename");
		InputList inputList = new InputList(inStream);
		assertEquals(true , inputList.getAllRows().isEmpty());	// _allRows initialized but empty
	
		inStream =  MapBuilder.class.getClassLoader().getResourceAsStream("countries.csv");
		inputList = new InputList(inStream);
		inputList.readCSV();
		assertEquals(247 , inputList.getAllRows().size());		// _allRows populated with all the rows
		
		assertEquals(6 , inputList.getAllRows().get(0).length);// _allRows populated with all the columns
		
		inStream = MapBuilder.class.getClassLoader().getResourceAsStream("runways.csv");
		inputList = new InputList(inStream);
		inputList.readCSV();
		assertEquals(39536 , inputList.getAllRows().size());
		assertEquals(20 , inputList.getAllRows().get(0).length);// _allRows populated with all the columns		
	}

}
