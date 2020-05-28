package auction_house.unit.actions;

import com.codesai.auction_house.business.actions.RetrieveAuctionAction;
import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.business.model.AuctionRepository;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RetrieveAuctionActionShould {

    @Test
    public void
    retrieve_an_auction_by_id() {
        AuctionRepository repository = mock(AuctionRepository.class);
        Double anyInitialPrice = new Random().nextDouble() + 1D;
        Double anyConquerPrice = anyInitialPrice + new Random().nextDouble();
        Auction expectedAuction = new Auction(anyInitialPrice, anyConquerPrice);

        when(repository.retrieveById(any())).thenReturn(expectedAuction);

        Auction auction = new RetrieveAuctionAction(repository).execute(expectedAuction.id);

        assertThat(auction).isEqualTo(expectedAuction);

    }

}