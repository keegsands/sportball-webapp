package com.bocano.softball.dao;

import java.util.List;

import com.bocano.softball.model.Campaign;

public interface CampaignDAO extends SimpleDAO<Campaign> {
	List<Campaign> findBySeason(final int seasonID);
}
