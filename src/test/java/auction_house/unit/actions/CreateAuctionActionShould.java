package auction_house.unit.actions;

import com.codesai.auction_house.business.actions.CreateAuctionAction;
import com.codesai.auction_house.business.actions.CreateAuctionRequest;
import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.business.model.AuctionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CreateAuctionActionShould {

    private static final String UUID_REGEX = "[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}";

    @Test
    public void
    create_an_auction() {
        AuctionRepository repository = mock(AuctionRepository.class);
        double anyInitialPrice = new Random().nextDouble() + 1D;
        double anyConquerPrice = anyInitialPrice + new Random().nextDouble();


        new CreateAuctionAction(repository).execute(new CreateAuctionRequest(
                String.valueOf(anyInitialPrice),
                String.valueOf(anyConquerPrice)));

        ArgumentCaptor<Auction> auctionCapture = ArgumentCaptor.forClass(Auction.class);

        verify(repository, times(1)).save(auctionCapture.capture());

        Auction auction = auctionCapture.getValue();

        assertThat(auction.initialPrice).isEqualTo(anyInitialPrice);
        assertThat(auction.conquerPrice).isEqualTo(anyConquerPrice);
        assertThat(auction.id).matches(UUID_REGEX);
    }

    @Test
    public void
    create_an_auction_with_initial_price_is_greater_than_conquer_price() {
        AuctionRepository repository = mock(AuctionRepository.class);

        double anyInitialPrice = new Random().nextDouble() + 1D;
        double anyConquerPrice = new Random().nextDouble() - anyInitialPrice;


        Assertions.assertThrows(RuntimeException.class, () -> {
            new CreateAuctionAction(repository).execute(new CreateAuctionRequest(
                    String.valueOf(anyInitialPrice),
                    String.valueOf(anyConquerPrice)));
        });
    }

}