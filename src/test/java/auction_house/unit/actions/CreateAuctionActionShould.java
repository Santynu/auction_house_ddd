package auction_house.unit.actions;

import com.codesai.auction_house.business.actions.CreateAuctionAction;
import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.business.model.AuctionRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CreateAuctionActionShould {

    @Test
    public void
    create_an_auction() {
        AuctionRepository repository = mock(AuctionRepository.class);
        Auction auctionToCreate = new Auction("anyId");

        new CreateAuctionAction(repository).execute(auctionToCreate.id);

        verify(repository, times(1)).save(auctionToCreate);
    }

}