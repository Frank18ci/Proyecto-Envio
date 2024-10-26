package com.envios.proyectoenvios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProxyController {
	 	@Autowired
	    private RestTemplate restTemplate;

	    private final String API_URL = "https://api.apis.net.pe/v2/reniec/dni?numero=";
	    private final String API_TOKEN = "apis-token-9564.YM3c4yuM8lmMA0lDpDOwjCZ-PISuHaVD";

	    @CrossOrigin(origins = "https://proyectoenvio-htexh6ced7h2ffa5.eastus-01.azurewebsites.net")
	    @GetMapping("/proxy/dni")
	    public ResponseEntity<String> consultaDNI(@RequestParam String numero) {
	        String url = API_URL + numero;
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "Bearer " + API_TOKEN);

	        HttpEntity<String> entity = new HttpEntity<>(headers);

	        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	        return response;
	    }
}
