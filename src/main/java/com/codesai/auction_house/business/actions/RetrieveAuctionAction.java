package com.codesai.auction_house.business.actions;

import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.business.model.AuctionRepository;

public class RetrieveAuctionAction {
    private final AuctionRepository repository;

    public RetrieveAuctionAction(AuctionRepository repository) {
        this.repository = repository;
    }

    public Auction execute(String auctionId) {
        return repository.retrieveById(auctionId);
    }
}
