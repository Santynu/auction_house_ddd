package com.codesai.auction_house.infrastructure.delivery_mechanism;

import com.codesai.auction_house.business.actions.CreateAuctionRequest;
import com.codesai.auction_house.business.model.Auction;
import com.codesai.auction_house.infrastructure.ActionFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.eclipse.jetty.client.HttpContent;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.net.http.HttpHeaders;
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
                response.header(HttpHeader.CONTENT_TYPE.asString(), "application/json");

                return auction.toString();
            });
            post("auction", (request, response) -> {
                JsonObject postParams = getBodyAsJSON(request);

                String auctionId = ActionFactory.createAuctionAction().execute(
                        new CreateAuctionRequest(postParams.get("initial_bid").toString(), postParams.get("conquer_price").toString()));
                response.header("Location", request.uri()+"/"+auctionId);
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

    private static JsonObject getBodyAsJSON(Request request) {
        return new Gson().fromJson(request.body(), JsonObject.class);
    }

    private static Object wipResponse(Response response) {
        response.status(500);
        return "WIP";
    }
}
