package bdd.steps.tc;

import org.givwenzen.annotations.DomainStep;
import org.givwenzen.annotations.DomainSteps;

import com.example.aatg.tc.TemperatureConverter;

@DomainSteps
public class TemperatureConverterSteps {

    private static final String CELSIUS = "Celsius";
    private static final String FAHRENHEIT = "Fahrenheit";
    
    private static final String ANY_TEMPERATURE =
        "([-+]?\\d+(?:\\.\\d+)?)";
    private static final String UNIT = "(C|F)";
    private static final String UNIT_NAME =
        "(" + CELSIUS + "|" + FAHRENHEIT + ")";
    
    private static final double DELTA = 0.01d;
    
    private double mValue = Double.NaN;
    
    @DomainStep("I(?: a|')m using the TemperatureConverter")
    public void createTemperatureConverter() {
        // do nothing
    }
    
    @DomainStep("I enter " + ANY_TEMPERATURE + " into " + UNIT_NAME + " field")
    public void setField(double value, String unitName) {
        mValue = value;
    }
    
    @DomainStep("I obtain " + ANY_TEMPERATURE + " in " + UNIT_NAME + " field")
    public boolean verifyConversion(double value, String unitName) {
        try {
            final double t = (FAHRENHEIT.compareTo(unitName) == 0) ? getFahrenheit() : getCelsius();
            return (Math.abs(t-value) < DELTA);
        }
        catch (RuntimeException ex) {
            return false;
        }
    }

    @DomainStep("Celsius")
    public double getCelsius() {
        return TemperatureConverter.fahrenheitToCelsius(mValue);
    }
    
    @DomainStep("Fahrenheit")
    public double getFahrenheit() {
        return TemperatureConverter.celsiusToFahrenheit(mValue);
    }
}
