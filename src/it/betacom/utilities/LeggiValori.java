package it.betacom.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LeggiValori {
	
	private static String path = "./resources/config.properties";

	// Metodo per leggere da file di configurazione i valori per effettuare la connessione al db 
 	public static String leggiValoriConfig(String param) {	
 		String res = "";
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(path);
		} catch (FileNotFoundException e2) {
			System.out.println("Errore caricamento dati dal file di properties " + path + " : " + e2.getMessage());
//	 					logger.error("Errore caricamento dati dal file di properties " + path + " : " + e2.getMessage());
		}
		
		if(inputStream == null) {
			System.out.println("Errore caricamento file di properties " + path);
//	 					logger.error("Errore caricamento file di properties " + path);
		}
		
//	 				logger.info("File di properties trovato: " + path);
		
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
//	 					db_user = properties.getProperty("db.user");
//	 					db_url = properties.getProperty("db.url");
//	 					db_password = properties.getProperty("db.password");
//	 					db_schema = properties.getProperty("db.schema");
////	 					System.out.println("Valori letti da file di properties: \n\t- URL: " + db_url + "\n\t- User: " + db_user + "\n\t- Password: " + db_password + "\n\t- Schema: " + db_schema);
//	 					logger.info("Valori letti da file di properties: URL = " + db_url + ", User = " + db_user + ", Password: " + db_password + ", Schema: " + db_schema);
////	 					System.out.println(properties.getProperty("script_create_schema"));
			res = properties.getProperty(param);
		} catch (IOException e) {
			System.out.println("Errore caricamento dati dal file di properties " + path + " : " + e.getMessage());
//	 					logger.error("Errore caricamento dati dal file di properties " + path + " : " + e.getMessage());
		}
		return res;
 	}
}
