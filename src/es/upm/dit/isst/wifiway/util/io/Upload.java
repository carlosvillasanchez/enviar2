package es.upm.dit.isst.wifiway.util.io;


import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import es.upm.dit.isst.wifiway.dao.*;
import es.upm.dit.isst.wifiway.dao.model.*;
import javax.json.*;

public class Upload {
	
	private static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
   }
	
	public static void main(String[] args) throws IOException {
		
		
		
		
		BufferedReader reader = new BufferedReader(
			new FileReader(new File("wifis.txt")));
				  
		String line = reader.readLine();
		
		
		//quitar la primera linea NO		  
		while( line!=null ){
			
			
			JsonReader jsonReader = Json.createReader(new StringReader(line));
			JsonObject object = jsonReader.readObject();
			jsonReader.close();
			
			JsonArray arrayWifis = object.getJsonArray("results");
			
			for (JsonValue wifiAux : arrayWifis){
				JsonObject wifiX = (JsonObject) (wifiAux);
				
				Wifi wifi = new Wifi();
				Point point = new Point();
				Strength strength = new Strength();
				
				if (wifiX.isNull("ssid")){
					wifi.setSsid("NULL");
				}else{
					wifi.setSsid(wifiX.getString("ssid"));
				}
				
				if (wifiX.isNull("netid")){
					wifi.setMAC("NULL");
				}else{
					wifi.setMAC(wifiX.getString("netid"));
				}
				
				
				
				if (wifiX.getString("freenet").equals("Y") ||  wifiX.getString("encryption").equals("none")) {
					wifi.setOpen(true);
					
				}else {
					wifi.setOpen(false);
					
				}
				
				String enc = wifiX.getString("encryption");
				wifi.setType(enc);
				//wep wpa wpa2 none unknown wpa3
				
				double lat =  (wifiX.getJsonNumber("trilat").doubleValue());
				double lng = (wifiX.getJsonNumber("trilong").doubleValue());
				
				point.setLatitud(redondearDecimales(lat, 3));
				point.setLongitud(redondearDecimales(lng, 3));
					
				point.setId();
				
				strength.setStregth(-30.0);
				
				strength.setPoint(point);
				strength.setWifi(wifi);
				strength.setId();
				
				
				Point pAux = PointDAOImplementation.getInstance().readPoint(point.getId());
				Wifi wAux = WifiDAOImplementation.getInstance().readWifi(wifi.getSsid());
				Strength sAux = StrengthDAOImplementation.getInstance().readStrength(strength.getId());
				
				if(pAux == null) {
					PointDAOImplementation.getInstance().createPoint(point);
				}else {
					System.out.println("PUNTO REPETIDO \n  \n \n ");
				}
				if(wAux == null) {
					WifiDAOImplementation.getInstance().createWifi(wifi);
				}else {
					System.out.println(wAux.getMAC() + "WIFI REPETIDO \n ---- \n ---- \n ---- ");
				}
				
				if(sAux == null) {
					StrengthDAOImplementation.getInstance().createStrength(strength);
				}
			}
			
			line = reader.readLine();
		}
		
		reader.close();
		return;
	}
}
