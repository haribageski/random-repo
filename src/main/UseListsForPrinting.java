package main;

import java.util.Scanner;

import data.Airport;
import data.Country;
import data.Runway;
import factory.MapFactory;

public class UseListsForPrinting {

	public static void main(String[] args) {
		MapFactory mapFactory = new MapFactory();
		mapFactory.populateMap();

		System.out.println("Please enter a country name or code in console...");
		Scanner sc = new Scanner(System.in);
		
		Country selectedCtry = null;
		String input , quitSign = "";
		
		
		while(true)
		{
			if(quitSign == "")
				input = sc.next();
			else
			{
				input = quitSign;
				quitSign = "";
			}
			if(mapFactory.getMapCtryCodeToCountry().containsKey(input.toUpperCase()))
				selectedCtry = (Country) mapFactory.getMapCtryCodeToCountry().getObjFromMap(input.toUpperCase());
			else
			if(mapFactory.getMapCtryNameToCountry().containsKey(input))
				selectedCtry = (Country) mapFactory.getMapCtryNameToCountry().getObjFromMap(input);
			else
			{
				System.out.println("No such country name or code, please try again or press 0 to exit...");
				quitSign = sc.next();
				if(quitSign.equals("0"))
					return;
				else
					continue;
			}
			
			if(selectedCtry == null)
			{
				System.out.println("Something went wrong");
				return;
			}
			
			
			selectedCtry.printObject();
			if(!mapFactory.getMapCtryIdToAirportId().containsKey(selectedCtry.getCode()))
			{
				System.out.println("\tThere is no data for airports associated with this country.");
				continue;
			}
			for(String ctryAirportId :  mapFactory.getMapCtryIdToAirportId().get(selectedCtry.getCode()))
			{
				System.out.println("ctryAirportId:" + ctryAirportId);
				Airport ctryAirport = (Airport) mapFactory.getMapAirportIdentToAirport().getObjFromMap(ctryAirportId);
				ctryAirport.printObject();
				if(!mapFactory.getMapAirportIdToRunwayId().containsKey(ctryAirportId))
				{
					System.out.println("\tThere is no data for runways associated with this airport.");
					continue;
				}
				for(String airportRwayId : mapFactory.getMapAirportIdToRunwayId().get(ctryAirportId))
				{
					Runway airportRway = (Runway) mapFactory.getMapRunwayIdToRunway().getObjFromMap(airportRwayId);
					airportRway.printObject();
				}
					
			}
		}
	}	
	
}
