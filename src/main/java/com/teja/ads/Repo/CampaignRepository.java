package com.teja.ads.Repo;


import com.teja.ads.Entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByTargetLocation(String targetLocation);
}
