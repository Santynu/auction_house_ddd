package com.codesai.auction_house.business.actions;

import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.business.model.AuctionRepository;

public class CreateAuctionAction {
    private final AuctionRepository repository;

    public CreateAuctionAction(AuctionRepository repository) {
        this.repository = repository;
    }

    public String execute(CreateAuctionRequest createAuctionRequest) {

        Auction auction = new Auction(
                createAuctionRequest.initialBid,
                createAuctionRequest.conquerPrice);

        repository.save(auction);
        return auction.id;
    }
}
