package com.nelsonssoares.swtest.starwarstest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nelsonssoares.swtest.starwarstest.domain.Planet;

public interface PlanetRepository extends JpaRepository<Planet,Long>{
    Optional<Planet>findByName(String name);   
}
