package com.projectagora.realtimebidder.scheduler;

import com.projectagora.realtimebidder.helper.MockCampaignApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class Scheduler {
    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Value("${global.scheduler.limit}")
    public int glocalSchedulerLimit;

    private final MockCampaignApi mockCampaignApi;

    private final Map<String, Integer> campaignsToBids = new HashMap<>();

    @Autowired
    public Scheduler(final MockCampaignApi mockCampaignApi) {
        this.mockCampaignApi = mockCampaignApi;
    }

    @PostConstruct
    public void loadSchedulerProperties() throws IOException {
        Arrays.stream(mockCampaignApi.getAllCampaigns())
                .forEach(campaign -> campaignsToBids.put(campaign.getId(), 0));
    }

    @Scheduled(fixedRate = 60_000)
    public void reset() {
        log.info("Resetting number of bids in the frame currently - all campaigns.");

        campaignsToBids
                .keySet()
                .forEach(campaignId -> campaignsToBids.replace(campaignId, 0));

        log.info("Complete.");
    }

    public Map<String, Integer> getCampaignsToBids() {
        return campaignsToBids;
    }

    public boolean campaignSchedulerLimit(String campaignId){
        return campaignsToBids.get(campaignId) < glocalSchedulerLimit;
    }
}
