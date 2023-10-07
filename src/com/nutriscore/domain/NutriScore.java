package com.nutriscore.domain;

/**
 * The possible values of the nutri-score
 */
public enum NutriScore {
	A("A", "#038141"),
	B("B", "#85BB2F"),
	C("C", "#FECB02"),
	D("D", "#EE8100"),
	E("E", "#E63E11");

	private final String letter;
	private final String color;

	private NutriScore(String letter, String color) {
		this.letter = letter;
		this.color = color;
	}

	public String getLetter() {
		return this.letter;
	}

	public String getColor() {
		return this.color;
	}
}
