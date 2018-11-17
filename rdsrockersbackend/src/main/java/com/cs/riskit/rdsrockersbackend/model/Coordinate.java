package com.cs.riskit.rdsrockersbackend.model;

public class Coordinate {

	private String label;
	private long y;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public long getY() {
		return y;
	}
	public void setY(long y) {
		this.y = y;
	}
	public Coordinate(String label, long y) {
		super();
		this.label = label;
		this.y = y;
	}
}
