package scraping.parser;

import java.sql.Date;

import scraping.database.PriceDataRepresentation;

/**
 * Class representing a parsed stock from Avanza.
 * 
 * @author Erik
 * 
 */
public class ParserStock {
	public String	name;
	public String 	market = "";
	public int 		volume;
	public double	lastClose;
	public double 	buy;
	public double 	sell;
	public Date		date;
	
	public ParserStock(String name){
		this.name = name;
	}
		
	@Override
	public boolean equals(Object o){
		return name.equals((String) o);
	}
	
	@Override 
	public String toString(){
		String str = "Aktie: " + name + " K�p: " + buy + " S�lj: " + sell + " volym: " + volume;
		return str;
	}
}
