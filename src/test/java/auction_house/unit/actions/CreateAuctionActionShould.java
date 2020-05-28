package auction_house.unit.actions;

import com.codesai.auction_house.business.actions.CreateAuctionAction;
import com.codesai.auction_house.business.actions.CreateAuctionRequest;
import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.business.model.AuctionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CreateAuctionActionShould {

    @Test
    public void
    create_an_auction() {
        AuctionRepository repository = mock(AuctionRepository.class);
        double anyInitialPrice = new Random().nextDouble() + 1D;
        double anyConquerPrice = anyInitialPrice + new Random().nextDouble();

        Auction auctionToCreate = new Auction("anyId", anyInitialPrice, anyConquerPrice);

        new CreateAuctionAction(repository).execute(new CreateAuctionRequest(auctionToCreate.id,
                String.valueOf(anyInitialPrice),
                String.valueOf(anyConquerPrice)));

        verify(repository, times(1)).save(auctionToCreate);
    }

    @Test
    public void
    create_an_auction_with_initial_price_is_greater_than_conquer_price() {
        AuctionRepository repository = mock(AuctionRepository.class);
        String anyId = "anyId";
        double anyInitialPrice = new Random().nextDouble() + 1D;
        double anyConquerPrice = new Random().nextDouble() - anyInitialPrice;


        Assertions.assertThrows(RuntimeException.class, () -> {
            new CreateAuctionAction(repository).execute(new CreateAuctionRequest(anyId,
                    String.valueOf(anyInitialPrice),
                    String.valueOf(anyConquerPrice)));
        });
    }

}