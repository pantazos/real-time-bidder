package com.projectagora.realtimebidder.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectagora.realtimebidder.domain.Campaign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MockCampaignApi {
    private final ResourceLoader resourceLoader;

    @Value("${campaign.mock.file}")
    private String campaignMockFile;

    public MockCampaignApi(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Campaign[] getAllCampaigns() throws IOException {
        Resource resource =
                resourceLoader.getResource("classpath:" + campaignMockFile);
        return new ObjectMapper().readValue(resource.getFile(), Campaign[].class);
    }
}
