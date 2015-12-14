package reading_data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
	private String _inFileName;
	// We will use topMeny HashMap to iterate through the parameters of a Country
	private List<String> _topMenu;
	private List<String> _rowVals;
	private List< List<String> > _allRows;
	
	InputList(){};
	public InputList(String inFileName)
	{
		this._inFileName = inFileName;
	    this._allRows = new ArrayList<List<String> >();
	    this._topMenu = new ArrayList<String>();
	}
	    
	
	public List< List<String> > getAllRows()
	{
		return this._allRows;
	}
	
	public void readFromExcel() throws IOException
	{
		try {
			InputStream is = new FileInputStream(this._inFileName );
		    POIFSFileSystem fs = new POIFSFileSystem(is);
		    HSSFWorkbook wb = new HSSFWorkbook(fs);
		    HSSFSheet sheet = wb.getSheetAt(0);
		    HSSFRow row;
		    HSSFCell cell;
	
		    int rows = sheet.getPhysicalNumberOfRows();		// No of rows
	
		    int maxColumnsInRows = 0; // No of columns
		    int numColsInCurrentRow = 0;
	
		    // This trick ensures that we get the data properly even if it doesn't start from first few rows
		    for(int i = 0; i < 10 || i < rows; i++) {
		        row = sheet.getRow(i);
		        if(row != null) {
		            numColsInCurrentRow = sheet.getRow(i).getPhysicalNumberOfCells();
		            if(numColsInCurrentRow > maxColumnsInRows) 
		            	maxColumnsInRows = numColsInCurrentRow;
		        }
		    }
	
		    for(int r = 0; r < rows; r++) 
		    {
		        row = sheet.getRow(r);
		        _rowVals = new ArrayList<String>();
		        if(row != null) 
		        {
		            for(int c = 0; c < maxColumnsInRows; c++) 
		            {
		                cell = row.getCell((short)c);
		                if(cell != null)
		                {
		                	if(r == 0)
		                		_topMenu.add(cell.toString());
		                	else
			                	_rowVals.add(cell.toString());
		                }
		                else
		                {
		                	if(r == 0)
		                		_topMenu.add("");
		                	else
		                		_rowVals.add("");
		                }
		            }
		        }
		        _allRows.add(_rowVals);
		    }
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
	}
}