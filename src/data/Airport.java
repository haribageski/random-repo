package data;

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
			
			try{
				if(!stringParams[4].isEmpty())
					_latitude_deg = Double.parseDouble(stringParams[4]);
				if(!stringParams[5].isEmpty())
					_longitude_deg = Double.parseDouble(stringParams[5]);
				if(!stringParams[6].isEmpty())
					_elevation_ft = Double.parseDouble(stringParams[6]);
			}
			catch (NumberFormatException e) {
				//e.printStackTrace();
			}
			
		}	
	};
	
	public String getCountryCode() {
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
	}

	@Override
	public void printObject() {

		System.out.print("\tAirport \"" + this.getAirportName() + "\", ");
		if(!this._municipality.equals(""))
			System.out.println("located in " + this._municipality + ".");
		if(!this._type.equals(""))
			System.out.println("\tIt is of type " + this._type + ".");
		if(this._scheduled_service)
			System.out.println("\tIt has a scheduled_service.");
		if(!this._home_link.equals(""))
			System.out.println("\tIt website is " + this._home_link + ".");
		if(!this._home_link.equals(""))
			System.out.println("\tYou can read more about this airport on wikipedia: " + this._wikipedia_link + ".");
		System.out.println();
	};
}

