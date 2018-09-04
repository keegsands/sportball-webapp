package com.bocano.softball.service.impl;

import org.springframework.stereotype.Service;

import com.bocano.softball.dao.FieldDAO;
import com.bocano.softball.model.Field;
import com.bocano.softball.service.FieldService;

@Service
public class FieldServiceImpl extends
		AbstractSimpleServiceImpl<Field, FieldDAO> implements FieldService {
	public void setFieldDAO(final FieldDAO fieldDAO) {
		super.setDAO(fieldDAO);
	}
}
