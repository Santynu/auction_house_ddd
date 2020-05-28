package com.codesai.auction_house.business.actions;

public class CreateAuctionRequest {
    public final String auctionId;
    public final Double initialPrice;
    public final Double conquerPrice;

    public CreateAuctionRequest(String auctionId, String initialPrice, String conquerPrice) {
        this.auctionId = auctionId;
        this.initialPrice = Double.parseDouble(initialPrice);
        this.conquerPrice = Double.parseDouble(conquerPrice);
    }

}
