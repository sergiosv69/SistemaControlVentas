package main;

import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;
import java.io.BufferedReader;
import java.text.ParseException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import views.SystemView;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;



public class main {
    public static String[][] consultDNIOld(String dni1) {
		String[][] getdni = new String[1][4];
                String dni="";
                String nombres="";
                String apellidoMaterno="";
                String apellidoPaterno="";
		try {
			HttpClientApache http = new HttpClientApache();
			String url = "https://dniruc.apisperu.com/api/v1/dni/"+dni1+"?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InNhYmlub3Zhc3F1ZXpzZXJnaW9hbmRlcnNvbkBnbWFpbC5jb20ifQ.MIJGPcxZ3EObiB7oMe09JfyZKXouxZnBHrzG-8LFTQc";
			BufferedReader rd_consultadni = http.sendGet(url);
			String name = rd_consultadni.toString();
			String line;
			while ((line = rd_consultadni.readLine()) != null) {
				name = line.replaceAll("\\|", "#");
                                //System.out.println((name));
                                
			}
                        JSONObject object = new JSONObject(name);
			 dni = object.getString("dni");
                        nombres = object.getString("nombres");
                        apellidoPaterno = object.getString("apellidoPaterno");
                        apellidoMaterno = object.getString("apellidoMaterno");
                        System.out.println((dni));
			System.out.println((nombres));
                        System.out.println((apellidoPaterno));
                        System.out.println((apellidoMaterno));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getdni;
	}
    public static void main(String[] args) {
  
        try {
                UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());
            } catch (ParseException | UnsupportedLookAndFeelException e) {
            }
        
            //SystemView formulario = new SystemView(); 
         //formulario.setVisible(true);
         System.out.println(consultDNIOld("76241576"));
       
    }
    
    
}
