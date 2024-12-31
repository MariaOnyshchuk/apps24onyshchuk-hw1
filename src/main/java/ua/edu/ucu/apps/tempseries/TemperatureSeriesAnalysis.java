package ua.edu.ucu.apps.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperSeries;
    private static double MIN_TEMPERATURE = -273.0;
    public TemperatureSeriesAnalysis() {
        this.temperSeries = new double[0];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        validateTemperatureSeries(temperatureSeries);
        this.temperSeries = temperatureSeries.clone();
    }

    private void validateTemperatureSeries(double[] temps) {
        for (double temp : temps) {
            if (temp < MIN_TEMPERATURE) {
                throw new InputMismatchException("Temperature cannot be less than " + MIN_TEMPERATURE + "°C");
            }
        }
    }

    public double average() {
        double sum = 0;
        if (temperSeries.length == 0){
            throw new IllegalArgumentException("The temperature series is empty....");
        } else{
            for (double a: temperSeries){
                sum+=a;
            }
        }
        return sum/temperSeries.length;
    }

    public double deviation() {
        if (temperSeries.length == 0){
            throw new IllegalArgumentException("The temperature series is empty....");
        } else{
            double avg = average();
            double sumSquares = 0;
            for (double temp : temperSeries) {
                sumSquares += Math.pow(temp - avg, 2);
            }
            return Math.sqrt(sumSquares / temperSeries.length);
        }
    }

    public double min() {
        if (temperSeries.length == 0){
            throw new IllegalArgumentException("The temperature series is empty....");
        } else{
            double min = temperSeries[0];
            for (double a: temperSeries){
                if (a<min) {
                    min = a;
                }
            }
            return min;
        }
    }

    public double max() {
        if (temperSeries.length == 0){
            throw new IllegalArgumentException("The temperature series is empty....");
        }

        double max = temperSeries[0];
        for (double a: temperSeries){
            if (a>max) {
                max = a;
            }
        }
        return max;

    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperSeries.length == 0){
            throw new IllegalArgumentException("The temperature series is empty....");
        }
        double closest = temperSeries[0];
        for (double a: temperSeries){
            if (Math.abs(a-tempValue)<Math.abs(closest-tempValue)){
                closest = a;
            } else if (Math.abs(a-tempValue)==Math.abs(closest-tempValue)){
                closest = Math.max(a, closest);
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (temperSeries.length == 0){
            throw new IllegalArgumentException("The temperature series is empty....");
        }

        int count = 0;
        for (double value : temperSeries) {
            if (value < tempValue) {
                count++;
            }
        }
        double[] lessTemp = new double[count];
        count = 0;
        for (double value: temperSeries){
            if (value<tempValue){
                lessTemp[count] = value;
                count++;
            }
        }
        return lessTemp;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (temperSeries.length == 0){
            throw new IllegalArgumentException("The temperature series is empty....");
        }
        int count = 0;
        for (double value : temperSeries) {
            if (value < tempValue) {
                count++;
            }
        }
        double[] greatTemp = new double[count];
        count = 0;
        for (double value: temperSeries){
            if (value>tempValue){
                greatTemp[count] = value;
                count++;
            }
        }
        return greatTemp;
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        if (temperSeries.length == 0){
            throw new IllegalArgumentException("The temperature series is empty....");
        }
        int count = 0;
        for (double value : temperSeries) {
            if (value <= upperBound && value>=lowerBound) {
                count++;
            }
        }
        double[] temp = new double[count];
        count = 0;
        for (double value: temperSeries){
            if (value<=upperBound && value>=lowerBound){
                temp[count] = value;
                count++;
            }
        }
        return temp;
    }

    public void reset() {
        temperSeries = new double[0];

    }

    public double[] sortTemps() {
        if (temperSeries.length == 0) {
            throw new IllegalArgumentException("The temperature series is empty....");
        }
//        double[] sortedTemps = Arrays.copyOf(temperSeries, temperSeries.length);
//        Arrays.sort(sortedTemps);
        Arrays.sort(temperSeries); // Sort the original array in-place
        return temperSeries;
//        return sortedTemps;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperSeries.length == 0) {
            throw new IllegalArgumentException("The temperature series is empty....");
        }
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        if (temperSeries.length == 0) {
            throw new IllegalArgumentException("The temperature series is empty....");
        }
        int i=0;
        if (temperSeries.length+ temps.length>temperSeries.length){
            i = temperSeries.length;
            temperSeries = Arrays.copyOf(temperSeries, 2*temperSeries.length);
        }
        for (double temp : temps) {
            temperSeries[i] = temp;
            i++;
        }
        return i;
    }
}
