package data;
import java.util.ArrayList;
import java.util.List;

public class Runway implements InputObject 
{

	private double _length_ft = 0,_width_ft = 0, _le_latitude_deg = 0, _le_longitude_deg = 0, 
				_le_elevation_ft = 0, _le_heading_degT = 0, _le_displaced_threshold_ft = 0, 
				_he_latitude_deg = 0, _he_longitude_deg = 0, _he_elevation_ft = 0,
				_he_heading_degT = 0, _he_displaced_threshold_ft = 0;
	private String _id = "", _airport_ref = "", _airport_ident = "", _surface = "", 
				_le_ident = "", _he_ident = "";
	private boolean _lighted = false, _closed = false;
	
	
	
	public Runway(String[] stringParams) 
	{
		if(stringParams.length > 0)
		{
			this._id = stringParams[0];
			this._airport_ref = stringParams[1];
			this._airport_ident = stringParams[2];
			this._surface = stringParams[5];
			this._le_ident = stringParams[8];
			this._he_ident = stringParams[14];
			
			this._length_ft = Double.parseDouble(stringParams[3]);
			this._width_ft = Double.parseDouble(stringParams[4]);
			this._le_latitude_deg = Double.parseDouble(stringParams[9]);
			this._le_longitude_deg = Double.parseDouble(stringParams[10]);
			this._le_elevation_ft = Double.parseDouble(stringParams[11]);
			this._le_heading_degT = Double.parseDouble(stringParams[12]);
			this._le_displaced_threshold_ft = Double.parseDouble(stringParams[13]);
			this._he_latitude_deg = Double.parseDouble(stringParams[15]);
			this._he_longitude_deg = Double.parseDouble(stringParams[16]);
			this._he_elevation_ft = Double.parseDouble(stringParams[17]);
			this._he_heading_degT = Double.parseDouble(stringParams[18]);
			this._he_displaced_threshold_ft = Double.parseDouble(stringParams[19]);
			
			this._lighted = Boolean.parseBoolean(stringParams[6]);
			this._closed =  Boolean.parseBoolean(stringParams[7]);		
		}
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
	
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj != null && obj.getClass().equals(this.getClass()))
			return ((Runway)obj).getCode().equals(this.getCode());
		return false;
	}
	
}
