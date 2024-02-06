package com.amadeus.project.domain.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "flights")
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "depart_airport_id", nullable = false)
    private AirportEntity departAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_airport_id")
    private AirportEntity arrivalAirport;

    @Column(name = "depart_date", nullable = false)
    private OffsetDateTime departDate;

    @Column(name = "arrival_date")
    private OffsetDateTime arrivalDate;

    @Column(name = "price", nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

}