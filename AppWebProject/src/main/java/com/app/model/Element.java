package com.app.model;

public class Element {

	// private String observation;
	private int time_period;
	private String ref_area;
	private String indicator;
	private String breakdown;
	private String unit_measure;
	private float value;
	// private String flag;
	// private String note;

	public Element(int time_period, String ref_area, String indicator, String breakdown, String unit_measure,
			float value)

	{ // this.observation=observation;
		this.time_period = time_period;
		this.ref_area = ref_area;
		this.indicator = indicator;
		this.breakdown = breakdown;
		this.unit_measure = unit_measure;
		this.value = value;
		// this.flag=flag;
		// this.note=note;
	}

	/*
	 * public String getObservation() { return observation; } public void
	 * setObservation(String observation) { this.observation = observation; }
	 */
	public int getTime_period() {
		return time_period;
	}

	public void setTime_period(int time_period) {
		this.time_period = time_period;
	}

	public String getRef_area() {
		return ref_area;
	}

	public void setRef_area(String ref_area) {
		this.ref_area = ref_area;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getBreakdown() {
		return breakdown;
	}

	public void setBreakdown(String breakdown) {
		this.breakdown = breakdown;
	}

	public String getUnit_measure() {
		return unit_measure;
	}

	public void setUnit_measure(String unit_measure) {
		this.unit_measure = unit_measure;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	/*
	 * public String getFlag() {
	 * 
	 * return flag; } public void setFlag(String flag) { this.flag = flag; } public
	 * String getNote() { return note; } public void setNote(String note) {
	 * this.note = note; }
	 */

	@Override
	public String toString() {
		return String.format(
				"Element [time_period:%d, ref_area=%s, indicator=%s, value=%f, breakdown=%s, unit_measure=%s]",
				time_period, ref_area, indicator, value, breakdown, unit_measure);
	}

}
