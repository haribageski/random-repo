package data;

public class Country implements InputObject
{
	public Country(String[] stringParams)
	{
		if(stringParams.length > 0)
		{
			_id = stringParams[0];
			_code = stringParams[1];
			_name = stringParams[2];
			_continent = stringParams[3];
			_wikipedia = stringParams[4];
			_keywords = stringParams[5];
		}
	}
	
	private String _id = "";
	private String _code = "";
	private String _name = "";
	private String _continent = "";
	private String _wikipedia = "";
	private String _keywords = "";
	
	
	@Override
	public String getCode()
	{
		return _code;
	}


	public String getCountryName() {
		return this._name;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj != null && obj.getClass().equals(this.getClass()))
			return ((Country)obj).getCode().equals(this.getCode());
		return false;
	}
	
	public int compare(Country ctry) {
		return this.getCountryName().compareTo(ctry.getCountryName());
	}


	@Override
	public void printObject() {
		System.out.println("The country " + this.getCountryName() + " is situated on the continent " +
				this._continent + ", ");
		System.out.println("and you can learn more about this country at " + this._wikipedia);
		System.out.println();
	};
}
