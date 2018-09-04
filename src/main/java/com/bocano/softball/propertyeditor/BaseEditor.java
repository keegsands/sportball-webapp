package com.bocano.softball.propertyeditor;

import java.beans.PropertyEditorSupport;

import com.bocano.softball.service.SimpleService;

public class BaseEditor<G, S extends SimpleService<G>> extends
		PropertyEditorSupport {
	private final S service;

	public BaseEditor(S service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		final int entityId = Integer.parseInt(text);
		if (entityId == -1) {
			return;
		}
		final G object = service.getById(entityId);
		setValue(object);
	}
}
