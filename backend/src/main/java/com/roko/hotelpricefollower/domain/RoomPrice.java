package com.roko.hotelpricefollower.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "room_prices")
public class RoomPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price_in_cents", precision = 6, scale = 0)
    private Long priceInCents;

    @Column(name = "departure_date", nullable = false)
    private LocalDate departureDate;

    @Column(name = "sold_out", nullable = false)
    private boolean soldOut;

    @Column(name = "additional_info")
    private String additionalInfo;

    @JoinColumn(name = "room_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Room room;

    @JoinColumn(name = "price_fetch_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private PriceFetch priceFetch;
}
