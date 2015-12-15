package main;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

import factory.MapFactory;
import factory.InputObjectMap;

public class UseListsForPrinting {

	public static void main(String[] args) {
		String type = "runway";
		MapFactory inputMapFactory;
		InputObjectMap inputObjMap;
		
		inputMapFactory= new MapFactory(type);
		inputObjMap = inputMapFactory.getMapFromInput();
		
	}	
}
