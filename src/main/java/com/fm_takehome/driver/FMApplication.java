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

import javax.annotation.PostConstruct;

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
import com.fm_takehome.models.Route;
import com.fm_takehome.models.RouteWrapper;
import com.fm_takehome.models.Stop;
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
public class FMApplication {
	
	@Autowired
    private StopAttributesService saService;
	
	@Autowired
    private StopService stopService;
	
	@Autowired
    private RouteService routeService;
	
	@Autowired
    private AttributeService attributeService;
	
    @PostConstruct
    public void loadData() throws URISyntaxException, IOException, InterruptedException {
    	
		//create a client
		HttpClient hc = HttpClient.newBuilder().build();
		ObjectMapper objectMapper = new ObjectMapper();
		
		//get routes
		HttpRequest request = HttpRequest.newBuilder()
			.uri(new URI("https://api-v3.mbta.com/routes?filter%5Btype%5D=0%2C1"))
			.GET()
			.build();
		
		HttpResponse<String> routesResponse = hc.send(request, HttpResponse.BodyHandlers.ofString());		
		String routesBody = (String)routesResponse.body();
		RouteWrapper rw = objectMapper.readValue(routesBody, RouteWrapper.class);
		
		
		//query the stops for each routes
		List<Route> routes = rw.getData();		
		for (Route route : routes) {
			StringBuilder routeList = new StringBuilder();
			routeList.append("https://api-v3.mbta.com/stops?include=route&filter%5Broute%5D=");	
			routeList.append(route.getId());
			HttpRequest stopsRequest = HttpRequest.newBuilder()
					.uri(new URI(routeList.toString()))
					.GET()
					.build();
			HttpResponse<String> stopsResponse = hc.send(stopsRequest, HttpResponse.BodyHandlers.ofString());		
			String stopsBody = (String)stopsResponse.body();
			StopWrapper sw = objectMapper.readValue(stopsBody, StopWrapper.class);
			//update stop list in model
			route.setStops(sw.getData());
		}
		
		//save model - should also populate stop table
		routeService.Save(rw.getData());
    }
	
	@GetMapping("/getSubwayRoutes")
	public List<String> getSubwayRoutes() throws URISyntaxException, IOException, InterruptedException {
		
		//load up routes from entity
		List<Route> routes = routeService.list();
		
		//total up number of routes where type is 0 or 1 using EF style syntax
		List<String> names = new ArrayList<String>();
		
		routes.forEach(route -> {
			Integer type = route.getAttributes().getType();
			String longName = route.getAttributes().getLong_name();
			if (type == 0 || type == 1) names.add(longName);
		});		
		
		return names;
	}
	
	@GetMapping("/getSubwayStopsByMunicipality")
	public List<String> getSubwayStopsByMunicipality(@RequestParam("town") String town) throws URISyntaxException, IOException, InterruptedException {
		
		//load up routes from entity
		List<Stop> stops = stopService.list();
		
		//total up number of routes where type is 0 or 1 using EF style syntax
		List<String> names = new ArrayList<String>();
		
		stops.forEach(stop -> {
			String municipality = stop.getAttributes().getMunicipality();
			String name = stop.getAttributes().getName();
			if (municipality.equals(town)) names.add(name);
		});		
		
		return names;
	}
	
	@GetMapping("/getSubwayRouteFewestStops")
	public String getSubwayRouteFewestStops() throws URISyntaxException, IOException, InterruptedException {
		
		Integer min = Integer.MAX_VALUE;
		String name = "";
		
		List<Route> routes = routeService.list();
		
		for (Route route : routes) {
			List<Stop> stops = route.getStops();
			if (stops.size() < min) {
				min = stops.size();
				name = route.getAttributes().getLong_name();
			}
		}
		
		return name + " has " + min + " stops.";
	}
	
	@GetMapping("/getSubwayRouteMostStops")
	public String getSubwayRouteMostStops() throws URISyntaxException, IOException, InterruptedException {
		Integer max = Integer.MIN_VALUE;
		String name = "";
		
		List<Route> routes = routeService.list();
		
		for (Route route : routes) {
			if (route.getStops().size() > max) {
				max = route.getStops().size();
				name = route.getAttributes().getLong_name();
			}
		}
		
		return name + " has " + max + " stops.";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FMApplication.class, args);
	}

}
