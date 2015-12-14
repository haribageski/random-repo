package data;

import java.util.ArrayList;
import java.util.List;

public class Airport implements InputObject{
	public Airport(String[] stringParams)
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
	};
	
	String _id, _ident, _type, _name, _continent, _iso_country, _iso_region, _municipality;
	String _gps_code, _iata_code, _local_code, _home_link, _wikipedia_link, _keywords;
	boolean _scheduled_service;
	double _latitude_deg, _longitude_deg, _elevation_ft;


	public String getCountryName() {
		return this._iso_country;
	}
	
	@Override
	public String getCode() {
		return this._ident;
	}
}

