package com.codesai.auction_house.infrastructure.delivery_mechanism;

import com.codesai.auction_house.business.ConquerPriceGreaterThantInitialBidException;
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

                String initial_bid = postParams.get("initial_bid").toString();
                String conquer_price = postParams.get("conquer_price").toString();
                try{
                    CreateAuctionRequest createAuctionRequest = new CreateAuctionRequest(initial_bid, conquer_price);
                    String auctionId = ActionFactory.createAuctionAction().execute(
                            createAuctionRequest);
                    response.header("Location", request.uri()+"/"+auctionId);
                    response.status(HttpStatus.CREATED_201);
                    return auctionId;

                }catch (ConquerPriceGreaterThantInitialBidException e){
                    response.status(HttpStatus.UNPROCESSABLE_ENTITY_422);
                    //response.body(
                      //      "name", "InitialBidIsGreaterThanConquerPrice"
                             //"description", "initial cannot be greater "+initial_bid+" than conquer price "+conquer_price);

                    return "InitialBidIsGreaterThanConquerPrice";


                }catch (Exception e){
                    response.status(HttpStatus.BAD_REQUEST_400);
                    return "The auction body is not well formed.";
                }

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
