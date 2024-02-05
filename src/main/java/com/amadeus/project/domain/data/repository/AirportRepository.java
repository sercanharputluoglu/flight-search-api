package com.amadeus.project.domain.data.repository;

import com.amadeus.project.domain.data.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, UUID> {
    Optional<AirportEntity> findByCity(String departCity);

}
