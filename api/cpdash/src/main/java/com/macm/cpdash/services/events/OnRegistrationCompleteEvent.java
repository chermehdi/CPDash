package com.macm.cpdash.services.events;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.macm.cpdash.domain.entities.UserEntity;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
	private static final long serialVersionUID = -6451558596509152203L;
	private UserEntity user;
	private Locale local;
	private String appUrl;

	public OnRegistrationCompleteEvent(UserEntity userEntity, Locale locale, String appUrl) {
		super(userEntity);
		this.user = userEntity;
		this.local = locale;
		this.appUrl = appUrl;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Locale getLocal() {
		return local;
	}

	public void setLocal(Locale local) {
		this.local = local;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
}
