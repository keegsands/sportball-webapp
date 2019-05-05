package org.keegsands.sportball.service.impl;

import java.util.List;

import org.keegsands.sportball.dao.CampaignDAO;
import org.keegsands.sportball.model.Campaign;
import org.keegsands.sportball.service.CampaignService;

public class CampaignServiceImpl extends AbstractSimpleServiceImpl<Campaign, CampaignDAO> implements CampaignService {

	public void setCampaignDAO(final CampaignDAO campaignDAO) {
		super.setDAO(campaignDAO);
	}
	@Override
	public List<Campaign> findBySeason(int seasonID) {
		return getDAO().findBySeason(seasonID);
	}

}
