package es.upm.dit.isst.wifiway.servlets;

import java.io.*;
import java.net.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.json.*;

import es.upm.dit.isst.wifiway.dao.model.*;
import es.upm.dit.isst.wifiway.dao.*;


@WebServlet("/PointServlet")
public class PointServlet extends HttpServlet{
	
	private static final String TOKEN1 = "QUlEMDMwNjNlZTRiNzg0MTRmNDUxNjVjZTY0OTU2MmIwN2Q6MDg0M2YzZGYxMzk0MTJjMDU5NzkyYjRmNDVmOGI4YmY=";
	
	private static double redondearDecimales(double valorInicial, int numeroDecimales) {
	        double parteEntera, resultado;
	        resultado = valorInicial;
	        parteEntera = Math.floor(resultado);
	        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
	        resultado=Math.round(resultado);
	        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
	        return resultado;
	   }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//double latitud = Double.parseDouble(req.getParameter("latitud"));
		//double longitud = Double.parseDouble(req.getParameter("longitud"));
		
		String lng = req.getParameter("lng");
		String lat = req.getParameter("lat");
		
		String email = req.getParameter("email");
		
		
		
		double lngRed = redondearDecimales(Double.parseDouble(lng), 3);
		double latRed = redondearDecimales(Double.parseDouble(lat), 3);
		
		System.out.println(lngRed);
		System.out.println(latRed);
		
		if(email != null) {
			User usuario = UserDAOImplementation.getInstance().readUser(email);		
			if (usuario != null) {
				String guardar = "" + (latRed) + "&" + lngRed;
				usuario.guardar(guardar);
				
			}
		}
		
		
		
		Point point = PointDAOImplementation.getInstance().get(latRed, lngRed);
		System.out.println(point);
		Wifi wifis[];
		List<Strength> str = new ArrayList<>();
		if (point == null) {
			double latMin = redondearDecimales(latRed - 0.0005, 4);
			double latMax = redondearDecimales(latRed + 0.0005, 4);
			
			double lngMin = redondearDecimales(lngRed - 0.0005, 4);
			double lngMax = redondearDecimales(lngRed + 0.0005, 4);
			
			String urlx =  "https://api.wigle.net/api/v2/network/search"
					+ "?onlymine=false&latrange1=" + latMin + "&latrange2=" + latMax  
					+ "&longrange1=" + lngMin + "&longrange2=" + lngMax
					+ "&showGsm=true&showCdma=true";
			
			URL url = new URL(urlx);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Authorization", "Basic " + this.TOKEN1);
			StringBuilder result = new StringBuilder();
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
			String response = result.toString();
			
			System.out.println(response);
			
			JsonReader jsonReader = Json.createReader(new StringReader(response));
			JsonObject object = jsonReader.readObject();
			jsonReader.close();
			
			JsonArray arrayWifis = object.getJsonArray("results");
			wifis = new Wifi[arrayWifis.size()];
			int index = 0;
			
			Point pointAux = new Point();
			
			pointAux.setLatitud(latRed);
			pointAux.setLongitud(lngRed);
			pointAux.setId();
			
			PointDAOImplementation.getInstance().createPoint(pointAux);
			
			
			for (JsonValue wifin : arrayWifis){
				
				JsonObject wifiX = (JsonObject) (wifin);
				
				Wifi wifiAux = new Wifi();
				
				Strength strength = new Strength();
				
				if (wifiX.isNull("ssid")){
					wifiAux.setSsid("NULL");
				}else{
					wifiAux.setSsid(wifiX.getString("ssid"));
				}
				
				if (wifiX.isNull("netid")){
					wifiAux.setMAC("NULL");
				}else{
					wifiAux.setMAC(wifiX.getString("netid"));
				}
				
				
				
				if (wifiX.getString("freenet").equals("Y") ||  wifiX.getString("encryption").equals("none")) {
					wifiAux.setOpen(true);
					
				}else {
					wifiAux.setOpen(false);
					
				}
				
				String enc = wifiX.getString("encryption");
				wifiAux.setType(enc);
				//wep wpa wpa2 none unknown wpa3
				
				
					
				
				strength.setStregth(-30.0);
				
				strength.setPoint(pointAux);
				strength.setWifi(wifiAux);
				strength.setId();
				
				
				
				Wifi wAux = WifiDAOImplementation.getInstance().readWifi(wifiAux.getSsid());
				Strength sAux = StrengthDAOImplementation.getInstance().readStrength(strength.getId());
				
				
				if(wAux == null) {
					WifiDAOImplementation.getInstance().createWifi(wifiAux);
					wifis[index] = wifiAux;
					
				}else {
					System.out.println(wAux.getMAC() + "WIFI REPETIDO \n ---- \n ---- \n ---- ");
					wifis[index] = wAux;
				}
				
				if(sAux == null) {
					StrengthDAOImplementation.getInstance().createStrength(strength);
				}
				index++;
				
			}
			
			//str = pointAux.getWifis();
	 
		}else{
			str = point.getWifis();
			System.out.println(str);
			System.out.println(str.size());
			int N = str.size();
			wifis = new Wifi[N];
			for (int i = 0; i<N; i++) {
				
				wifis[i] = str.get(i).getWifi();
			}
			
		}
		
		/*System.out.println(point);
		String[] parts = point.split("\\(");
		double longitud = Double.parseDouble(parts[0]); 
		double latitud = Double.parseDouble(parts[1]);*/
		//String latitud = "123";
		
		//ponemos bien las coordenadas (decimales)
		
		//Point point = PointDAOImplementation.getInstance().get(longitud, latitud);
		
		
		
		
		/*if (null == point) {
			//PETICION A LA BASE DE DATOS
		}
		

		else {
			//req.getSession().setAttribute("", );
			resp.sendRedirect(req.getContextPath() + "/.jsp");
		}*/
		
		
		
		  
		/*  
      
		//Wifi wifis[] = new Wifi[1];
		
		String wifis = "[" + wifi.toString() +"]";
		   
		System.out.print(wifis);
		req.getSession().setAttribute("lngitud", longitud);
		req.getSession().setAttribute("latitud", latitud);
		req.getSession().setAttribute("wifis", wifis);
		*/
		
		JsonArrayBuilder array = Json.createArrayBuilder();
		for(int i = 0; i < wifis.length; i++) {
			JsonObjectBuilder wifi = Json.createObjectBuilder()
	                .add("SSID", wifis[i].getSsid())
	                .add("MAC", wifis[i].getMAC())
	                .add("SignalStrength", "-30")
	                .add("Type", wifis[i].getType())
	                ;
			array.add(wifi);
			
		}
		JsonArray sol = array.build();
		
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.print(sol);
		out.close();
		
		
	}
	
}


