package com.codesai.auction_house.business.model;

public interface AuctionRepository {
    Auction retrieveById(String auctionId);

    boolean save(Auction auction);

}