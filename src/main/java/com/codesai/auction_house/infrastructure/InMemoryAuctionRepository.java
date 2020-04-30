package com.codesai.auction_house.infrastructure;

import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.business.model.AuctionRepository;

public class InMemoryAuctionRepository implements AuctionRepository {

    @Override
    public Auction retrieveById(String auctionId) {
        return new Auction(auctionId);
    }
}
