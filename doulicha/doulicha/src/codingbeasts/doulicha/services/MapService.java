package codingbeasts.doulicha.services;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Scanner;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MapService {
 public static double[] getCoordinates(String address) throws IOException, ParseException {
    address = address.replaceAll(" ", "+");
    String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + address;
    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("User-Agent", "Mozilla/5.0");
    InputStream in = conn.getInputStream();
    Scanner scanner = new Scanner(in);
    scanner.useDelimiter("\\A");
    String response = scanner.hasNext() ? scanner.next() : "";
    scanner.close();
    double[] coords = new double[2];
    JSONParser parser = new JSONParser();
    JSONArray array = (JSONArray) parser.parse(response);
    if (array.size() > 0) {
        JSONObject obj = (JSONObject) array.get(0);
        if (obj.containsKey("lat") && obj.containsKey("lon")) {
            String latStr = obj.get("lat").toString();
            if (latStr.matches("[\\d\\.\\-]+")) {
                coords[0] = Double.parseDouble(latStr);
            } else {
                System.out.println("Erreur: la chaîne extraite pour la latitude n'est pas un nombre valide");
                // Gérer cette situation, comme lancer une exception ou afficher un message d'erreur
            }
            String lonStr = obj.get("lon").toString();
            if (lonStr.matches("[\\d\\.\\-]+")) {
                coords[1] = Double.parseDouble(lonStr);
            } else {
                System.out.println("Erreur: la chaîne extraite pour la longitude n'est pas un nombre valide");
                // Gérer cette situation, comme lancer une exception ou afficher un message d'erreur
            }
        }
    }
    return coords;
}

   
}