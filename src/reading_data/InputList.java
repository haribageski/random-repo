package reading_data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputList 
{
	private InputStream _inFileStream = null;
	// We will use topMeny HashMap to iterate through the parameters of a Country
	private String[] _topMenu = null;
	private List< String[] > _allRows = null;
	
	InputList(){};
	public InputList(InputStream inStream)
	{
		this._inFileStream = inStream;
	    this._allRows = new ArrayList<String[]>();
	}
	    
	
	public List< String[]> getAllRows()
	{
		return this._allRows;
	}
	
	public void readCSV() throws IOException
	{
		//System.out.println(this._inFileStream.toString());
		if(this._inFileStream == null)
		{
			System.out.println("_inFileStream in InputList is null");
			return;
		}
			
		BufferedReader bfrReader = new BufferedReader(new InputStreamReader(this._inFileStream));;
		String line = "";
		String cvsSplitBy = ",";

		try {
			while ((line = bfrReader.readLine()) != null) 
			{
			    // use comma as separator
				line = line.replace("\"","") ;
				String[] row = line.split(cvsSplitBy,-1);
				
				
				 // if every col is empty, the line will equal col-1 delimiters
							
				if(this._topMenu == null)
				{
					this._topMenu = row;
				}
				else
				if(line.length() > row.length - 1)
				{
					this._allRows.add(row);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bfrReader != null) {
				try {
					bfrReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}