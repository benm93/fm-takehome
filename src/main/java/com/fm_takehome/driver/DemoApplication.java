package com.fm_takehome.driver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.BodySubscribers;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.concurrent.CompletionStage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fm_takehome.model.RouteWrapper;

@SpringBootApplication
@RestController
public class DemoApplication {
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
	return String.format("Hello %s!", name);
	}
	
	@GetMapping("/fetch")
	public String hello() throws URISyntaxException, IOException, InterruptedException {
		// create a client
		HttpRequest request = HttpRequest.newBuilder()
			.uri(new URI("https://api-v3.mbta.com/routes"))
			.GET()
			.build();
		
		HttpClient hc = HttpClient.newBuilder().build();
		
		BodySubscriber<String> bs = BodySubscribers.ofString(Charset.defaultCharset());
		
		HttpResponse<String> response = hc.send(request, HttpResponse.BodyHandlers.ofString());
		
		String body = (String)response.body();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		RouteWrapper rw = objectMapper.readValue(body, RouteWrapper.class);
		
		response.toString();
		
		return "MBTA fetch endpoint!";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
