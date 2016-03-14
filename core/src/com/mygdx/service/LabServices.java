package com.mygdx.service;

public interface LabServices {
	public String JEWELRY = "jewelry";

	// (arbitrary) request code for the purchase flow
	static final int RC_REQUEST = 10001;

	public void buyJewelry();

	public void processPurchases();
}
