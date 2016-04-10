package org.myapp.model;

public enum OffenseType {

	HOMEWORK_NOT_DONE(1), STEALING(2), FIGHTING(0.5), UNTIDYNESS(1), LYING(1.5), SCHOOL_PROPERTY_DAMAGE(1);

	double period;

	OffenseType(double period) {
		this.period = period;
	}

	public double getPeriod() {
		return period;
	}
}