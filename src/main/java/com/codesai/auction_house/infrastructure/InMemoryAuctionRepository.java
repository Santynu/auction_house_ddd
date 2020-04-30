package com.codesai.auction_house.infrastructure;

import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.business.model.AuctionRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAuctionRepository implements AuctionRepository {

    private Map<String, Auction> auctions = new HashMap<>();

    @Override
    public Auction retrieveById(String auctionId) {
        return auctions.get(auctionId);
    }

    @Override
    public boolean save(Auction auction) {
        auctions.put(auction.id, auction);
        return true;
    }
}
