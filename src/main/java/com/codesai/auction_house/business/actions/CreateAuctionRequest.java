package com.codesai.auction_house.business.actions;

public class CreateAuctionRequest {

    public final Double initialBid;
    public final Double conquerPrice;

    public CreateAuctionRequest(String initialBid, String conquerPrice) {

        this.initialBid = Double.parseDouble(initialBid);
        this.conquerPrice = Double.parseDouble(conquerPrice);
    }

}
