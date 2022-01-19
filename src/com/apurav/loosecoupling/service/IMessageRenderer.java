package com.apurav.loosecoupling.service;

public interface IMessageRenderer {

	void render();
	void setMessageProvider(IMessageProvider provider);
}
