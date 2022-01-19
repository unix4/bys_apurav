package com.apurav.loosecoupling;

import com.apurav.loosecoupling.service.IMessageRenderer;

public class Main {

	public static void main(String[] args) {
		IMessageRenderer renderer = (IMessageRenderer) ApuravFramework.beanRegistry.get("renderer");
		renderer.render();
	}
}
