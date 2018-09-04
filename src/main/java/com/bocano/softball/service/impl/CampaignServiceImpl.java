package com.bocano.softball.service.impl;

import java.util.List;

import com.bocano.softball.dao.CampaignDAO;
import com.bocano.softball.model.Campaign;
import com.bocano.softball.service.CampaignService;

public class CampaignServiceImpl extends AbstractSimpleServiceImpl<Campaign, CampaignDAO> implements CampaignService {

	public void setCampaignDAO(final CampaignDAO campaignDAO) {
		super.setDAO(campaignDAO);
	}
	@Override
	public List<Campaign> findBySeason(int seasonID) {
		return getDAO().findBySeason(seasonID);
	}

}
