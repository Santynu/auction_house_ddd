package com.codesai.auction_house.infrastructure;

import com.codesai.auction_house.business.actions.CreateAuctionAction;
import com.codesai.auction_house.business.actions.RetrieveAuctionAction;

public class ActionFactory {

    public static RetrieveAuctionAction retrieveAuctionAction() {
        return new RetrieveAuctionAction(new InMemoryAuctionRepository());
    }

    public static CreateAuctionAction createAuctionAction() {
        return new CreateAuctionAction(new InMemoryAuctionRepository());
    }

}
