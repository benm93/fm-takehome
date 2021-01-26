package com.fm_takehome.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm_takehome.models.Route;
import com.fm_takehome.repositories.RouteRepository;

@Service
public class RouteService {
	@Autowired
    private RouteRepository routeRepository;

    public List<Route> list() {
        return routeRepository.findAll();
    }
    
    public void Save(List<Route> sa) {
    	routeRepository.saveAll(sa);
    }
}
