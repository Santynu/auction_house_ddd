package com.codesai.auction_house.business.actions;

import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.business.model.AuctionRepository;

public class CreateAuctionAction {
    private final AuctionRepository repository;

    public CreateAuctionAction(AuctionRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String auctionId) {
        Auction auction = new Auction(auctionId);

        return repository.save(auction);
    }
}
