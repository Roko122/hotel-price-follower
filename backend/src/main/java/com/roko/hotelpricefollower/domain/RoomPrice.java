package com.roko.hotelpricefollower.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "sold_out")
    private boolean soldOut;

    @Column(name = "additional_info")
    private String additionalInfo;

    @JoinColumn(name = "room_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

}
