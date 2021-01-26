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
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fm_takehome.models.RouteWrapper;
import com.fm_takehome.models.StopAttributes;
import com.fm_takehome.models.StopWrapper;
import com.fm_takehome.repositories.StopAttributeRepository;
import com.fm_takehome.services.AttributeService;
import com.fm_takehome.services.RouteService;
import com.fm_takehome.services.StopAttributesService;
import com.fm_takehome.services.StopService;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.fm_takehome.models"})  // scan JPA entities
@EnableJpaRepositories("com.fm_takehome.repositories")
@ComponentScan({"com.fm_takehome.services"})
public class DemoApplication {
	
	@Autowired
    private StopAttributesService saService;
	
	@Autowired
    private StopService stopService;
	
	@Autowired
    private RouteService routeService;
	
	@Autowired
    private AttributeService attributeService;
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
	return String.format("Hello %s!", name);
	}
	
	@GetMapping("/fetch")
	public String hello() throws URISyntaxException, IOException, InterruptedException {
		
		//create a client
		HttpClient hc = HttpClient.newBuilder().build();
		ObjectMapper objectMapper = new ObjectMapper();
		
		//get routes		
		HttpRequest request = HttpRequest.newBuilder()
			.uri(new URI("https://api-v3.mbta.com/routes"))
			.GET()
			.build();
		
		HttpResponse<String> routesResponse = hc.send(request, HttpResponse.BodyHandlers.ofString());		
		String routesBody = (String)routesResponse.body();
		RouteWrapper rw = objectMapper.readValue(routesBody, RouteWrapper.class);
		
		routeService.Save(rw.getData());
		
		//get stops
		HttpRequest stopsRequest = HttpRequest.newBuilder()
				.uri(new URI("https://api-v3.mbta.com/stops"))
				.GET()
				.build();
		
		HttpResponse<String> stopsResponse = hc.send(stopsRequest, HttpResponse.BodyHandlers.ofString());		
		String stopsBody = (String)stopsResponse.body();		
		StopWrapper sw = objectMapper.readValue(stopsBody, StopWrapper.class);
		
		List<StopAttributes> sa = new ArrayList<StopAttributes>();
		
		stopService.Save(sw.getData());
		
		//sw.getData().forEach(stop -> sa.add(stop.getAttributes()));
		//saService.Save(sa);
		
//		String url = "jdbc:sqlite:C:/sqlite/db/" + "mbta.db";
//
//        try (Connection conn = DriverManager.getConnection(url)) {
//            if (conn != null) {
//                DatabaseMetaData meta = conn.getMetaData();
//                System.out.println("The driver name is " + meta.getDriverName());
//                System.out.println("A new database has been created.");
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
		
		return "MBTA fetch endpoint!";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
