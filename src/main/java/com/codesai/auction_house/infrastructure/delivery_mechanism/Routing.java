package com.codesai.auction_house.infrastructure.delivery_mechanism;

import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.infrastructure.ActionFactory;
import org.eclipse.jetty.http.HttpStatus;
import spark.Response;

import java.util.Optional;
import java.util.UUID;

import static spark.Spark.*;

public class Routing {

    public final static Integer PORT = 9001;

    public static void startApi() {
        System.out.println("starting auction house API");
        port(PORT);

        get("status", (req, res) -> "OK");
        path("api/", () -> {
            get("auction/:id", (request, response) -> {
                Auction auction = ActionFactory.retrieveAuctionAction().execute(request.params(":id"));
                response.status(HttpStatus.OK_200);
                return auction.toString();
            });
            post("auction", (request, response) -> {
                String auctionId = UUID.randomUUID().toString();
                ActionFactory.createAuctionAction().execute(auctionId);
                response.status(HttpStatus.CREATED_201);
                return auctionId;
            });
            post("auction/:id/bid", (request, response) -> wipResponse(response));
            post("auction/:auction_id/conquer", (request, response) -> wipResponse(response));
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        options("/*", (request, response) -> {
            var accessControlRequestHeaders = Optional.ofNullable(request.headers("Access-Control-Request-Headers"));
            accessControlRequestHeaders.ifPresent(c -> response.header("Access-Control-Allow-Headers", c));
            var accessControlRequestMethod = Optional.ofNullable(request.headers("Access-Control-Request-Method"));
            accessControlRequestMethod.ifPresent(c -> response.header("Access-Control-Allow-Methods", c));
            return "OK";
        });


    }

    private static Object wipResponse(Response response) {
        response.status(500);
        return "WIP";
    }
}
