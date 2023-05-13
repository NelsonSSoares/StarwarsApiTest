package com.nelsonssoares.swtest.starwarstest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.querydsl.binding.QuerydslPredicateBuilder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.PlatformFeatureDetector;

import com.nelsonssoares.swtest.starwarstest.domain.Planet;
import com.nelsonssoares.swtest.starwarstest.domain.QueryBuilder;
import com.nelsonssoares.swtest.starwarstest.repository.PlanetRepository;

@Service
public class PlanetService {

    private PlanetRepository planetRepository;
    
    public PlanetService(PlanetRepository planetRepository){
        this.planetRepository = planetRepository;
    }

    public Planet create(Planet planet){
        return planetRepository.save(planet);
    }

    public Optional<Planet> get(Long id){
        return planetRepository.findById(id);
    }

    public Optional<Planet> getByName(String name) {
        return planetRepository.findByName(name);
    }

    public List<Planet> list(String terrain, String climate, String name) {
        Example<Planet> query = QueryBuilder.makeQuery(new Planet(terrain, climate));
        return planetRepository.findAll(query);
    }

    public static List<Planet> list(String terrain, String climate) {
        return null;
    }
}
