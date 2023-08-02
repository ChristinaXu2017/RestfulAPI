package com.example.xu.xuCalculator;

public class XuCalculator {
	private String startDate;
	private String endDate;
	
	public XuCalculator(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	@Override
	public String toString() {
		return "XuCalculator [startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
}
