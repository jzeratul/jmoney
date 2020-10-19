package org.playground.jmoneyserver.model;

public enum ButtonVariant {

	PRIMARY("primary"),
	SECONDARY("secondary"),
	SUCCESS("success"),
	WARNING("warning"),
	DANGER("danger"),
	LIGHT("light"),
	LINK("link");
	
	private String name;
	
	ButtonVariant(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
