package auction_house.integration;


import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.infrastructure.InMemoryAuctionRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryAuctionRepositoryShould {

    @Test
    public void get_auction_by_id(){

        InMemoryAuctionRepository inMemoryAuctionRepository = new InMemoryAuctionRepository();
        Auction expectedAuction = new Auction("any_id");

        Auction auction = inMemoryAuctionRepository.retrieveById("any_id");

        assertThat(auction).isEqualTo(expectedAuction);
    }


}


