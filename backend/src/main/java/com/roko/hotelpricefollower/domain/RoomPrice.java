package com.roko.hotelpricefollower.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(
        name = "room_prices",
        indexes = {
                @Index(name = "idx_price", columnList = "price_in_cents"),
                @Index(name = "idx_departure_date", columnList = "departure_date")
        }
)
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
    private Room room;

    @JoinColumn(name = "price_fetch_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private PriceFetch priceFetch;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoomPrice roomPrice = (RoomPrice) o;
        return soldOut == roomPrice.soldOut && Objects.equals(id, roomPrice.id) && Objects.equals(priceInCents, roomPrice.priceInCents) && Objects.equals(departureDate, roomPrice.departureDate) && Objects.equals(additionalInfo, roomPrice.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, priceInCents, departureDate, soldOut, additionalInfo);
    }

    @Override
    public String toString() {
        return "RoomPrice{" +
                "id=" + id +
                ", priceInCents=" + priceInCents +
                ", departureDate=" + departureDate +
                ", soldOut=" + soldOut +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
