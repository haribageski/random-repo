package factory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import data.InputObject;
import reading_data.InputList;

public class InputObjectMap 
{
	private HashMap<String, InputObject> _inputMap;

	InputObjectMap()
	{
		this._inputMap = new HashMap<String, InputObject> ();
	}
	
	protected void putToInputMap(String str, InputObject inObj)
	{
		this._inputMap.put(str, inObj);
	}
	
	protected InputObject getObjFromMap(String str)
	{
		return this._inputMap.get(str);
	}
	
	protected Set<String> getAllKeysFromMap()
	{
		return this._inputMap.keySet();
	}
	
}
