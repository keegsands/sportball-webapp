package com.bocano.softball.service;

import java.util.List;

import com.bocano.softball.model.Campaign;

public interface CampaignService extends SimpleService<Campaign> {
	List<Campaign> findBySeason(final int seasonID);
}
