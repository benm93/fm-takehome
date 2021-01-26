package com.fm_takehome.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm_takehome.models.Attribute;
import com.fm_takehome.models.Route;
import com.fm_takehome.repositories.AttributesRepository;
import com.fm_takehome.repositories.RouteRepository;

@Service
public class AttributeService {
	@Autowired
    private AttributesRepository attributeRepository;

    public List<Attribute> list() {
        return attributeRepository.findAll();
    }
    
    public void Save(List<Attribute> sa) {
    	attributeRepository.saveAll(sa);
    }
}
