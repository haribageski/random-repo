package data;

import java.util.ArrayList;
import java.util.List;

public class Airport implements InputObject{
	public Airport(List<String> stringParams)
	{
		_id = stringParams.get(0);
		_ident = stringParams.get(1);
		_type = stringParams.get(2);
		_name = stringParams.get(3);
		_continent = stringParams.get(7);
		_iso_country = stringParams.get(8);
		_iso_region = stringParams.get(9);
		_municipality = stringParams.get(10);
		_gps_code = stringParams.get(12);
		_iata_code = stringParams.get(13);
		_local_code = stringParams.get(14);
		_home_link = stringParams.get(15);
		_wikipedia_link = stringParams.get(16);
		_keywords = stringParams.get(17);
		
		_scheduled_service = Boolean.parseBoolean(stringParams.get(11));
		
		_latitude_deg = Double.parseDouble(stringParams.get(4));
		_longitude_deg = Double.parseDouble(stringParams.get(5));
		_elevation_ft = Double.parseDouble(stringParams.get(6));
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

