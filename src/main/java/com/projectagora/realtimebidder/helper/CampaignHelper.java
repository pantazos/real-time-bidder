package com.projectagora.realtimebidder.helper;

import com.projectagora.realtimebidder.domain.Campaign;
import com.projectagora.realtimebidder.scheduler.Scheduler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CampaignHelper {
    private final MockCampaignApi mockCampaignApi;
    private final Scheduler scheduler;

    public CampaignHelper(final MockCampaignApi mockCampaignApi, final Scheduler scheduler) {
        this.mockCampaignApi = mockCampaignApi;
        this.scheduler = scheduler;
    }

    public Optional<Campaign> getCampaignHighestPayment(String country)
            throws IOException {
        return Arrays.stream(mockCampaignApi.getAllCampaigns())
                .filter(campaign -> campaign.getTargetedCountries().contains(country))
                .filter(campaign -> scheduler.campaignSchedulerLimit(campaign.getId()))
                .min((campaign1, campaign2) -> Double.compare(campaign2.getPrice(), campaign1.getPrice()))
                .map(campaign -> {
                    Integer bids = scheduler.getCampaignsToBids().get(campaign.getId());
                    scheduler.getCampaignsToBids().replace(campaign.getId(), ++bids);
                    return Optional.of(campaign);
                })
                .orElse(Optional.empty());
    }
}
