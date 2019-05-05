package org.keegsands.sportball.dao;

import java.util.List;

import org.keegsands.sportball.model.Campaign;

public interface CampaignDAO extends SimpleDAO<Campaign> {
	List<Campaign> findBySeason(final int seasonID);
}
