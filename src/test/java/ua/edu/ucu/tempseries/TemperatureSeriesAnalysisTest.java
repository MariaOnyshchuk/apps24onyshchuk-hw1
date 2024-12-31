package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;
import ua.edu.ucu.apps.tempseries.TempSummaryStatistics;
import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void test() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);

    }

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult;
        expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
        //----------------------------------------------------
        double[] temperatureSeries2 = {-1.0, 2.0, 5};
        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);
        expResult = 2.0;
        actualResult = seriesAnalysis2.average();
        assertEquals(expResult, actualResult, 0.00001);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }
    @Test
    public void testDeviation(){
        double[] temperatureSeries = {10.0, 12.0, 14.0, 16.0, 18.0};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expectedDeviation = 2.8284;
        assertEquals(expectedDeviation, analysis.deviation(), 0.0001);
    }
    @Test
    public void testMin(){
        double[] temperatureSeries = {10.0, 12.0, 14.0, 16.0, 18.0};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expected = 10.0;
        assertEquals(expected, analysis.min(), 0.0001);
        //---------------------------------------------------------
        double[] temperatureSeries2 = {18.0};
        TemperatureSeriesAnalysis analysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);
        expected = 18.0;
        assertEquals(expected, analysis2.min(), 0.0001);
    }
    @Test
    public void testMax(){
        double[] temperatureSeries = {10.0, 12.0, 14.0, 16.0, 18.0};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expected = 18.0;
        assertEquals(expected, analysis.max(), 0.0001);
        //---------------------------------------------------------
        double[] temperatureSeries2 = {18.0};
        TemperatureSeriesAnalysis analysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);
        expected = 18.0;
        assertEquals(expected, analysis2.max(), 0.0001);
    }

    @Test
    public void testClosestToZero(){
        double[] temperatureSeries = {10.0, -10.0, 14.0, 16.0};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expected = 10.0;
        assertEquals(expected, analysis.findTempClosestToZero(), 0.0001);
        //---------------------------------------------------------
        double[] temperatureSeries2 = {18.0};
        TemperatureSeriesAnalysis analysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);
        expected = 18.0;
        assertEquals(expected, analysis2.findTempClosestToZero(), 0.0001);
    }

    @Test
    public void testClosestToValue(){
        double[] temperatureSeries = {10.0, -10.0, 14.0, 16.0};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expected = 10.0;
        assertEquals(expected, analysis.findTempClosestToValue(5), 0.0001);
        //---------------------------------------------------------
        double[] temperatureSeries2 = {18.0};
        TemperatureSeriesAnalysis analysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);
        expected = 18.0;
        assertEquals(expected, analysis2.findTempClosestToValue(-10), 0.0001);
    }

    @Test
    public void testFindTempsLessThen(){
        double[] temperatureSeries = {10.0, 12.5, 15.0, 20.0, 5.0, 8.0};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // Check for temperatures less than 12.0
        double[] expected = {10.0, 5.0, 8.0};
        double[] actual = analysis.findTempsLessThen(12.0);

        assertArrayEquals(expected, actual, 0.0001);
    }

    @Test
    public void testFindTempsGreaterThen(){
        double[] temperatureSeries = {10.0, 12.5, 15.0, 20.0, 5.0, 8.0};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // Check for temperatures less than 12.0
        double[] expected = {12.5, 15.0, 20.0};
        double[] actual = analysis.findTempsGreaterThen(12.0);

        assertArrayEquals(expected, actual, 0.0001);
    }

    @Test
    public void testFindTempsInRange(){
        double[] temperatureSeries = {10.0, 12.5, 15.0, 20.0, 5.0, 8.0};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // Check for temperatures less than 12.0
        double[] expected = {5.0, 8.0};
        double[] actual = analysis.findTempsInRange(5, 8);

        assertArrayEquals(expected, actual, 0.0001);
    }
    @Test
    public void testReset() {
        double[] tempSeries = {1.0, -2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);

        assertEquals(3, seriesAnalysis.sortTemps().length);

        seriesAnalysis.reset();

        // After reset, the temperature series should be empty
        assertThrows(IllegalArgumentException.class, seriesAnalysis::average);

    }

    @Test
    public void testSortTemps() {
        double[] tempSeries = {3.0, -1.0, 5.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);
        double[] sorted = seriesAnalysis.sortTemps();
        double[] expected = {-1.0, 2.0, 3.0, 5.0};
        assertArrayEquals(expected, sorted, 0.00001);
    }

    @Test
    public void testSummaryStatisticsWithNonEmptySeries() {
        double[] tempSeries = {3.0, -1.0, 5.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);

        TempSummaryStatistics summary = seriesAnalysis.summaryStatistics();

        // Validate summary statistics
        assertEquals(2.25, summary.getAvgTemp(), 0.00001);   // Average
        assertEquals(2.16506, summary.getDevTemp(), 0.00001); // Deviation
        assertEquals(-1.0, summary.getMinTemp(), 0.00001);    // Min
        assertEquals(5.0, summary.getMaxTemp(), 0.00001);     // Max
    }
    @Test
    public void testSummaryStatisticsWithEmptySeries() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();

        // Ensure that the method throws an exception when series is empty
        assertThrows(IllegalArgumentException.class, seriesAnalysis::summaryStatistics);
    }
    @Test
    public void testAddTemps() {
        double[] tempSeries = {3.0, -1.0, 5.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);

        // Add temperatures to the series
        int newLength = seriesAnalysis.addTemps(6.0, -3.0, 4.0);

        // Validate the new length of the series
        assertEquals(7, newLength);
    }


}
