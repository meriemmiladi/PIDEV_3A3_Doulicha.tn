/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.entities;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class translator {
  private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
  private static final String CLIENT_SECRET = "PUBLIC_SECRET";
  private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";

  /**
   * Sends a POST request to the WhatsMate Translation API to translate text.
   *
   * @param fromLang the source language
   * @param toLang the target language
   * @param text the text to translate
   * @throws Exception if an error occurs while sending the request
   */
  public static String translate(String fromLang, String toLang, String text) throws Exception {
    // Build the JSON payload
    String jsonPayload = new StringBuilder()
      .append("{")
      .append("\"fromLang\":\"")
      .append(fromLang)
      .append("\",")
      .append("\"toLang\":\"")
      .append(toLang)
      .append("\",")
      .append("\"text\":\"")
      .append(text)
      .append("\"")
      .append("}")
      .toString();

    // Create the HTTP connection and set the request properties
    URL url = new URL(ENDPOINT);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestMethod("POST");
    conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
    conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
    conn.setRequestProperty("Content-Type", "application/json");

    // Write the JSON payload to the request body
    OutputStream os = conn.getOutputStream();
    os.write(jsonPayload.getBytes());
    os.flush();
    os.close();

    // Read the response from the API
    int statusCode = conn.getResponseCode();
    BufferedReader br = new BufferedReader(new InputStreamReader(
        (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
      ));
    String output = "";
    String line;
    while ((line = br.readLine()) != null) {
        output += line;
    }

    // Close the connection
    conn.disconnect();
      System.out.println(output);
    return output;
  }
}
