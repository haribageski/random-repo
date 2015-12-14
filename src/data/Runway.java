package data;
import java.util.ArrayList;
import java.util.List;

public class Runway implements InputObject 
{

	private double _length_ft ,_width_ft, _le_latitude_deg, _le_longitude_deg, 
				_le_elevation_ft, _le_heading_degT, _le_displaced_threshold_ft, 
				_he_latitude_deg, _he_longitude_deg, _he_elevation_ft, _he_heading_degT, 
				_he_displaced_threshold_ft;
	private String _id, _airport_ref, _airport_ident, _surface, _le_ident, _he_ident;
	private boolean _lighted, _closed;
	
	
	
	public Runway(List<String> stringParams) 
	{
		//if(boolParams.size()!=0)		TODO: correct it
		{
			System.out.println("The size of some list for setting parameters is incorrect");
		}
		
		this._id = stringParams.get(0);
		this._airport_ref = stringParams.get(1);
		this._airport_ident = stringParams.get(2);
		this._surface = stringParams.get(5);
		this._le_ident = stringParams.get(8);
		this._he_ident = stringParams.get(14);
		
		this._length_ft = Double.parseDouble(stringParams.get(3));
		this._width_ft = Double.parseDouble(stringParams.get(4));
		this._le_latitude_deg = Double.parseDouble(stringParams.get(9));
		this._le_longitude_deg = Double.parseDouble(stringParams.get(10));
		this._le_elevation_ft = Double.parseDouble(stringParams.get(11));
		this._le_heading_degT = Double.parseDouble(stringParams.get(12));
		this._le_displaced_threshold_ft = Double.parseDouble(stringParams.get(13));
		this._he_latitude_deg = Double.parseDouble(stringParams.get(15));
		this._he_longitude_deg = Double.parseDouble(stringParams.get(16));
		this._he_elevation_ft = Double.parseDouble(stringParams.get(17));
		this._he_heading_degT = Double.parseDouble(stringParams.get(18));
		this._he_displaced_threshold_ft = Double.parseDouble(stringParams.get(19));
		
		this._lighted = Boolean.parseBoolean(stringParams.get(6));
		this._closed =  Boolean.parseBoolean(stringParams.get(7));		
	};
															
	
	@Override
	public String getCode() 
	{
		return this._id.toString();
	}
	
	public String gerAirportId()
	{
		return this._airport_ident;
	}
}
