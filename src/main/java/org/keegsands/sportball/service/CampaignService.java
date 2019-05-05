package org.keegsands.sportball.service;

import java.util.List;

import org.keegsands.sportball.model.Campaign;

public interface CampaignService extends SimpleService<Campaign> {
	List<Campaign> findBySeason(final int seasonID);
}
