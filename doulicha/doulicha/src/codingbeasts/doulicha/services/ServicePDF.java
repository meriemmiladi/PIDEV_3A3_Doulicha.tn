/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

/**
 *
 * @author Admin
 */

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ServicePDF {
    
    private String apiKey;

    public ServicePDF(String apiKey) {
        this.apiKey = apiKey;
    }
 
    public void generatePdf(String nom, String evenement, int montant, String fileName) throws IOException, DocumentException {
    // Générer un PDF avec les informations de participation
    Document document = new Document();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PdfWriter.getInstance(document, outputStream);
    document.open();
    document.add(new Paragraph("Nom: " + nom));
    document.add(new Paragraph("Événement: " + evenement));
    document.add(new Paragraph("Montant: " + montant));
    document.close();

    // Convertir le PDF en flux de données
    byte[] pdfBytes = outputStream.toByteArray();

    // Envoyer le PDF au serveur web
    URL url = new URL("http://192.168.226.1/myservice/savepdf.php");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/pdf");
    connection.setRequestProperty("X-API-KEY", apiKey);
    connection.setDoOutput(true);

    // Ajouter le nom de fichier au corps de la requête
    String query = "fileName=" + URLEncoder.encode(fileName, "UTF-8");
    OutputStream os = connection.getOutputStream();
    os.write(query.getBytes("UTF-8"));
    os.write(pdfBytes);
    os.flush();
    os.close();

    // Récupérer la réponse du serveur web
    int responseCode = connection.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK) {
        String responseMessage = connection.getResponseMessage();
        System.out.println("PDF enregistré avec succès sur le serveur web: " + responseMessage);
    } else {
        System.out.println(responseCode);
         System.out.println("Erreur lors de l'enregistrement du PDF sur le serveur web");
        }
        connection.disconnect();
        
    }
       
}

