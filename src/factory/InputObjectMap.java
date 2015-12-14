package factory;

import java.io.IOException;
import java.util.HashMap;

import data.InputObject;
import reading_data.InputList;

public class InputObjectMap {
	private String _type;
	private HashMap<String, InputObject> _inputMap;

	InputObjectMap(String type)
	{
		this._type = type;
		this._inputMap = new HashMap<String, InputObject> ();
	}
	
	protected void putToInputMap(String str, InputObject inObj)
	{
		this._inputMap.put(str, inObj);
	}
	
	protected InputObject getObjFromMap(String str, InputObject inObj)
	{
		return this._inputMap.get(str);
	}
	
	
}
