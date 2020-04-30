package auction_house.integration;


import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.infrastructure.InMemoryAuctionRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryAuctionRepositoryShould {

    private InMemoryAuctionRepository inMemoryAuctionRepository = new InMemoryAuctionRepository();;

    @Test
    public void get_auction_by_id(){
        Auction givenAuction = new Auction("any_id");
        inMemoryAuctionRepository.save(givenAuction);

        Auction expectedAuction = inMemoryAuctionRepository.retrieveById("any_id");

        assertThat(expectedAuction).isEqualTo(givenAuction);
    }
    
    @Test
    public void save_auction() {
        Auction givenAuction = new Auction("any_id");
        boolean saved = inMemoryAuctionRepository.save(givenAuction);

        Auction expectedAuction = inMemoryAuctionRepository.retrieveById(givenAuction.id);

        assertTrue(saved);
        assertThat(expectedAuction).isEqualTo(givenAuction);
    }

}


