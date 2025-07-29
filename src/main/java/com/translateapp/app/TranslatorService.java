package com.translateapp.app;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class TranslatorService {
    private final String endpoint = "https://api.cognitive.microsofttranslator.com/";
    private final String path = "translate?api-version=3.0";
    private final String region = "southcentralus";
    private final String subscriptionkey = "9mdmNgACUF6F2OtLPnsayRqkIugfynOVPpLoj2gPZtg9thMapoVrJQQJ99BGACLArgHXJ3w3AAAbACOG8U66";


    public String translate(String text, String from, String to){
        RestTemplate restTemplate = new RestTemplate();

        // Si 'from' es null o vacío, no lo incluimos en la URL
        String url = endpoint + path + (from != null && !from.isBlank() ? "&from=" + from : "") + "&to=" + to;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Ocp-Apim-Subscription-Key", subscriptionkey);
        headers.set("Ocp-Apim-Subscription-Region", region);

        List<Map<String, String>> body = List.of(Map.of("Text", text));
        HttpEntity<List<Map<String, String>>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<List> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    List.class
            );

            if (response.getBody() == null || response.getBody().isEmpty()) {
                return "Error: Respuesta vacía.";
            }

            Map<String, Object> first = (Map<String, Object>) response.getBody().get(0);
            Object translationsObj = first.get("translations");

            if (translationsObj instanceof List<?> translationsList && !translationsList.isEmpty()) {
                Map<String, String> translationMap = (Map<String, String>) translationsList.get(0);
                return translationMap.get("text");
            }

            return "Error: No se encontró traducción.";
        } catch (Exception e) {
            return "Error en la traducción: " + e.getMessage();
        }
    }


}
