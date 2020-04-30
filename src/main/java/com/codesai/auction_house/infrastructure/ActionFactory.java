package com.codesai.auction_house.infrastructure;

import com.codesai.auction_house.business.model.Auction;

public class ActionFactory {

    public static Auction retrieveAuctionById(String id) {
        return new InMemoryAuctionRepository().retrieveById(id);
    }

}
