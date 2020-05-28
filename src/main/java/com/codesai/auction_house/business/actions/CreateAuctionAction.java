package com.codesai.auction_house.business.actions;

import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.business.model.AuctionRepository;

import java.util.UUID;

public class CreateAuctionAction {
    private final AuctionRepository repository;

    public CreateAuctionAction(AuctionRepository repository) {
        this.repository = repository;
    }

    public boolean execute(CreateAuctionRequest createAuctionRequest) {

        Auction auction = new Auction(
                createAuctionRequest.initialPrice,
                createAuctionRequest.conquerPrice);

        return repository.save(auction);
    }
}
