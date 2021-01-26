package com.fm_takehome.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm_takehome.models.Stop;
import com.fm_takehome.repositories.StopRepository;

@Service
public class StopService {
	@Autowired
    private StopRepository stopRepository;

    public List<Stop> list() {
        return stopRepository.findAll();
    }
    
    public void Save(List<Stop> sa) {
    	stopRepository.saveAll(sa);
    }
}
