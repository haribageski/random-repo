package data;

import java.util.ArrayList;
import java.util.List;

public class Country implements InputObject
{
	public Country(String[] stringParams)
	{
		_id = stringParams[0];
		_code = stringParams[1];
		_name = stringParams[2];
		_continent = stringParams[3];
		_wikipedia = stringParams[4];
		_keywords = stringParams[5];
	}
	
	private String _id;
	private String _code;
	private String _name;
	private String _continent;
	private String _wikipedia;
	private String _keywords;
	
	
	@Override
	public String getCode()
	{
		return _code;
	}


	public String getCountryName() {
		return this._name;
	}
}
