package com.fm_takehome.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm_takehome.models.StopAttributes;
import com.fm_takehome.repositories.StopAttributeRepository;

@Service
public class StopAttributesService {
	@Autowired
    private StopAttributeRepository stopAttributeRepository;

    public List<StopAttributes> list() {
        return stopAttributeRepository.findAll();
    }
    
    public void Save(List<StopAttributes> sa) {
    	stopAttributeRepository.saveAll(sa);
    }
}
