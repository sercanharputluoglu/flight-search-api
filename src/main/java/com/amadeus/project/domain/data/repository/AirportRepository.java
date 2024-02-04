package com.amadeus.project.domain.data.repository;

import com.amadeus.project.domain.data.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, String> {
}
