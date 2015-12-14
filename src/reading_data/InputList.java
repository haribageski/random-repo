package reading_data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import data.Country;

public class InputList 
{
	private InputStream _inFileStream;
	// We will use topMeny HashMap to iterate through the parameters of a Country
	private String[] _topMenu;
	private String[] _rowVals;
	private List< String[] > _allRows;
	
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
			boolean isFirstLine = true;
			while ((line = bfrReader.readLine()) != null) 
			{
			    // use comma as separator
				String[] row = line.split(cvsSplitBy,-1);
				if(isFirstLine)
				{
					this._topMenu = row;
					isFirstLine = false;
				}
				else
				{
					for(int i = 0; i < row.length; i++)
						System.out.print(row[i] + '\t');
					System.out.println();
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