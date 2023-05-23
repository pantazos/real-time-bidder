package com.projectagora.realtimebidder.controller;

import com.projectagora.realtimebidder.domain.Bid;
import com.projectagora.realtimebidder.domain.BidRequest;
import com.projectagora.realtimebidder.domain.BidResponse;
import com.projectagora.realtimebidder.domain.Campaign;
import com.projectagora.realtimebidder.helper.CampaignHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/v1/bid")
public class BidController {
    private final CampaignHelper campaignHelper;

    @Autowired
    public BidController(final CampaignHelper campaignHelper) {
        this.campaignHelper = campaignHelper;
    }

    @PostMapping
    public ResponseEntity<BidResponse> bid(@RequestBody BidRequest bidRequest)
            throws IOException {
        AtomicReference<ResponseEntity<BidResponse>> responseEntityAtomicReference =
                new AtomicReference<>();
        Optional<Campaign> campaignPaymentHighest =
                campaignHelper.getCampaignHighestPayment(
                        bidRequest.getDevice().getGeolocation().getCountry());
        campaignPaymentHighest.ifPresent(
                campaign -> {
                    Bid bid = new Bid(campaign.getId(), campaign.getPrice(), campaign.getAdCreative());
                    responseEntityAtomicReference.set(new ResponseEntity<>(
                            new BidResponse(bidRequest.getId(), bid), HttpStatus.OK));
                }
        );
        return responseEntityAtomicReference.get();
    }
}
