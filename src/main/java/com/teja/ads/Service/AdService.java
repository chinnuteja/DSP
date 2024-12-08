package com.teja.ads.Service;


import com.teja.ads.Entity.Ad;
import com.teja.ads.Repo.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;

    public Ad getAdForCampaign(Long campaignId) {
        return adRepository.findById(campaignId).orElseThrow(() -> new RuntimeException("Ad not found"));
    }

    public void incrementImpressions(Ad ad) {
        ad.setImpressions(ad.getImpressions() + 1);
        adRepository.save(ad);
    }

    public void trackClick(Long adId) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Ad not found"));
        ad.setClicks(ad.getClicks() + 1);
        adRepository.save(ad);
    }
}
