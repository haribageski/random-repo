package data;

import java.util.ArrayList;
import java.util.List;

public class Airport implements InputObject
{
	String _id = "", _ident = "", _type = "", _name = "", _continent = "", _iso_country = "",
			_iso_region = "", _municipality = "", _gps_code = "", _iata_code = "", 
			_local_code = "", _home_link = "", _wikipedia_link = "", _keywords = "";
	boolean _scheduled_service = false;
	double _latitude_deg = 0, _longitude_deg = 0, _elevation_ft = 0;


	public Airport(String[] stringParams)
	{
		if(stringParams.length > 0)
		{
			_id = stringParams[0];
			_ident = stringParams[1];
			_type = stringParams[2];
			_name = stringParams[3];
			_continent = stringParams[7];
			_iso_country = stringParams[8];
			_iso_region = stringParams[9];
			_municipality = stringParams[10];
			_gps_code = stringParams[12];
			_iata_code = stringParams[13];
			_local_code = stringParams[14];
			_home_link = stringParams[15];
			_wikipedia_link = stringParams[16];
			_keywords = stringParams[17];
			
			_scheduled_service = Boolean.parseBoolean(stringParams[11]);
			
			_latitude_deg = Double.parseDouble(stringParams[4]);
			_longitude_deg = Double.parseDouble(stringParams[5]);
			_elevation_ft = Double.parseDouble(stringParams[6]);
		}	
	};
	
	public String getCountryName() {
		return this._iso_country;
	}
	
	public String getAirportName() {
		return this._name;
	}
	
	@Override
	public String getCode() {
		return this._ident;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj != null && obj.getClass().equals(this.getClass()))
			return ((Airport)obj).getCode().equals(this.getCode());
		return false;
	}
	
	public int compare(Airport obj) {
		return this.getAirportName().compareTo(obj.getAirportName());
	};
}

