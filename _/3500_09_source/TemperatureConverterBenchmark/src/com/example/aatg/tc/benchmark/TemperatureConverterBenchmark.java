package com.example.aatg.tc.benchmark;


import java.util.Random;
import com.example.aatg.tc.TemperatureConverter;
import com.google.caliper.Param;
import com.google.caliper.SimpleBenchmark;

/**
 * Caliper Benchmark.<br>
 * To run the benchmarks in this class:<br>
 * {@code $ CLASSPATH=... caliper com.example.aatg.tc.benchmark.TemperatureConverterBenchmark.CelsiusToFahrenheitBenchmark} [-Dsize=n]
 *
 * @author diego
 *
 */
public class TemperatureConverterBenchmark {

	public static class CelsiusToFahrenheitBenchmark extends SimpleBenchmark {

		private static final double T = 10; // some temp

		@Param
		int size;

		private double[] temps;

		@Override
		protected void setUp() throws Exception {
			super.setUp();
			temps = new double[size];
			Random r = new Random(System.currentTimeMillis());
			for (int i=0; i < size; i++) {
				temps[i] = T * r.nextGaussian();
			}
		}

		public final void timeCelsiusToFahrenheit(int reps) {
			for (int i=0; i < reps; i++) {
				for (double t: temps) {
					TemperatureConverter.celsiusToFahrenheit(t);
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("This is a caliper benchmark.");
	}
}
