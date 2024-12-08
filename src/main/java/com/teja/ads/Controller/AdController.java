package com.teja.ads.Controller;

import com.teja.ads.Entity.Ad;
import com.teja.ads.Entity.Campaign;
import com.teja.ads.Service.AdService;
import com.teja.ads.Service.DspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ads")
public class AdController {
    @Autowired
    private DspService dspService;

    @Autowired
    private AdService adService;

    @GetMapping("/serve")
    public Map<String, String> serveAd(@RequestParam String location) {
        Campaign winningCampaign = dspService.getWinningCampaign(location);
        if (winningCampaign != null) {
            dspService.deductBudget(winningCampaign);
            Ad ad = adService.getAdForCampaign(winningCampaign.getId());
            adService.incrementImpressions(ad);

            Map<String, String> response = new HashMap<>();
            response.put("ad_id", ad.getId().toString());
            response.put("ad_content", ad.getContent());
            response.put("tracking_url", "/ads/track-click?ad_id=" + ad.getId());
            return response;
        }
        throw new RuntimeException("No eligible campaigns");
    }

    @GetMapping("/track-click")
    public void trackClick(@RequestParam Long ad_id) {
        adService.trackClick(ad_id);
    }
}
