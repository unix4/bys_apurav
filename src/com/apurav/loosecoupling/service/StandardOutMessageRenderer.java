package com.apurav.loosecoupling.service;

public class StandardOutMessageRenderer implements IMessageRenderer{

	private IMessageProvider messageProvider = null;
	
	@Override
	public void render() {
		if (messageProvider == null) {
			throw new RuntimeException("You must set message Provider.");
		}
		System.out.println(messageProvider.getMessage());
	}

	@Override
	public void setMessageProvider(IMessageProvider provider) {
		System.out.println("Setter-Call has been invoked ");
		this.messageProvider = provider;
	}
	
	public boolean isMessageProvider(){return false;}

}
