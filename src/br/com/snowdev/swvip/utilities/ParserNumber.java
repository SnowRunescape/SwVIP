package br.com.snowdev.swvip.utilities;

public class ParserNumber {
	public static int parseInt(String value){
		try {
			return Integer.parseInt(value);
		} catch(NumberFormatException e){
			
		}
		
		return 0;
	}
}
