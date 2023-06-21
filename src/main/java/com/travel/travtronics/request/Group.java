package com.travel.travtronics.request;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Group {

	@JsonProperty("lines")
	public ArrayList<Line> lines;

	public Group() {
		this.lines = new ArrayList<>();

	}

	public Group(ArrayList<Line> lines) {
		this.lines = lines;
	}

	public ArrayList<Line> getLines() {
		return lines;
	}

	public void setLines(ArrayList<Line> lines) {

		this.lines = lines;
	}

}
