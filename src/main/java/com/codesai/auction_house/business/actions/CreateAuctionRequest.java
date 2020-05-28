package com.codesai.auction_house.business.actions;

public class CreateAuctionRequest {

    public final Double initialPrice;
    public final Double conquerPrice;

    public CreateAuctionRequest(String initialPrice, String conquerPrice) {

        this.initialPrice = Double.parseDouble(initialPrice);
        this.conquerPrice = Double.parseDouble(conquerPrice);
    }

}
