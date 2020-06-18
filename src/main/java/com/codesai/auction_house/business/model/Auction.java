package com.codesai.auction_house.business.model;

import com.codesai.auction_house.business.ConquerPriceGreaterThantInitialBidException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public class Auction {

    public final String id;
    public final Double initialPrice;
    public final Double conquerPrice;

    public Auction(Double initialPrice, Double conquerPrice) {
        this.id = UUID.randomUUID().toString();
        this.initialPrice = initialPrice;
        this.conquerPrice = conquerPrice;

        validate();
    }

    private void validate() {
        if(initialPrice > conquerPrice)
            throw new ConquerPriceGreaterThantInitialBidException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Auction auction = (Auction) o;

        return new EqualsBuilder()
                .append(id, auction.id)
                .append(initialPrice, auction.initialPrice)
                .append(conquerPrice, auction.conquerPrice)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(initialPrice)
                .append(conquerPrice)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id='" + id + '\'' +
                ", initialPrice=" + initialPrice +
                ", conquerPrice=" + conquerPrice +
                '}';
    }
}
