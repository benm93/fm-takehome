package com.fm_takehome.driver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.fm_takehome.models.Route;
import com.fm_takehome.models.Stop;
import com.fm_takehome.models.StopAttributes;
import com.fm_takehome.services.RouteService;
import com.fm_takehome.services.StopAttributesService;
import com.fm_takehome.services.StopService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteRepositoryTest {

    @Autowired
    private RouteService routeService;
    
    @Autowired
    private StopService stopService;

    @Test
    public void checkRouteRepo() {
        List<Route> routes = routeService.list();

        boolean found = false;
        
        for (Route route : routes) {
        	if ("Red Line".equals(route.getAttributes().getLong_name())) {
        		found = true;
        	}
        }
        
        //assertEquals(routes.size(), 5);
        assertTrue(found);
    }
    
    @Test
    public void checkStopRepo() {
        List<Stop> stops = stopService.list();

        boolean found = false;
        
        for (Stop stop : stops) {
        	if ("Davis".equals(stop.getAttributes().getName())) {
        		found = true;
        	}
        }
        
        //assertEquals(routes.size(), 5);
        assertTrue(found);
    }
}
