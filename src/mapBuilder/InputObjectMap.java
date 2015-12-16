package mapBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import data.InputObject;

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
	
	public InputObject getObjFromMap(String str)
	{
		return this._inputMap.get(str);
	}
	
	protected Set<String> getAllKeysFromMap()
	{
		return this._inputMap.keySet();
	}
	
	public void printListOfObjects()
	{
		Set<String> keys =  this.getAllKeysFromMap();
		Iterator iterator = keys.iterator(); 
		while (iterator.hasNext())
		{
			String key = (String) iterator.next();
			this.getObjFromMap(key).printObject();
		}
	}
	
	public boolean containsKey(String key)
	{
		return this._inputMap.containsKey(key);
	}
}
