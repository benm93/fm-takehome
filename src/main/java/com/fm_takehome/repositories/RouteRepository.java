package com.fm_takehome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fm_takehome.models.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
	
}
