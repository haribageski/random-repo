package data;

import java.util.ArrayList;
import java.util.List;

public class Country implements InputObject
{
	public Country(List<String> stringParams)
	{
		_id = stringParams.get(0);
		_code = stringParams.get(1);
		_name = stringParams.get(2);
		_continent = stringParams.get(3);
		_wikipedia = stringParams.get(4);
		_keywords = stringParams.get(5);
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
