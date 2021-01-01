package com.example.aatg.tc.test.fitnesse.fixture;


import com.example.aatg.tc.TemperatureConverter;

public class TemperatureConverterCelsiusToFahrenheitFixture {
	private double celsius;

	public void setCelsius(double celsius) {
		this.celsius = celsius;
	}

	public String fahrenheit() throws Exception {

		try {
			return String.valueOf(TemperatureConverter.celsiusToFahrenheit(celsius));
		}
		catch (RuntimeException e) {
			return e.getLocalizedMessage();
		}

	}
}
