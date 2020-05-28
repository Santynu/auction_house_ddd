package com.codesai.auction_house.business.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Auction {

    public final String id;
    private final Double initialPrice;
    private final Double conquerPrice;

    public Auction(String id, Double initialPrice, Double conquerPrice) {
        this.id = id;
        this.initialPrice = initialPrice;
        this.conquerPrice = conquerPrice;

        validate();
    }

    private void validate() {
        if(initialPrice > conquerPrice)
            throw new RuntimeException();
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
}
