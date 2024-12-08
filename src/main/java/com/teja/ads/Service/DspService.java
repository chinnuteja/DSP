package com.teja.ads.Service;


import com.teja.ads.Entity.Campaign;
import com.teja.ads.Repo.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DspService {
    @Autowired
    private CampaignRepository campaignRepository;

    public Campaign getWinningCampaign(String location) {
        List<Campaign> campaigns = campaignRepository.findByTargetLocation(location);
        return campaigns.stream()
                .filter(c -> c.getRemainingBudget() >= c.getBidAmount())
                .max(Comparator.comparingDouble(Campaign::getBidAmount))
                .orElse(null);
    }

    public void deductBudget(Campaign campaign) {
        campaign.setRemainingBudget(campaign.getRemainingBudget() - campaign.getBidAmount());
        campaignRepository.save(campaign);
    }
}
