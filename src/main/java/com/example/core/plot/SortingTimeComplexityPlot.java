package com.example.core.plot;

import com.example.core.util.SortingUtility;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.Random;

public class SortingTimeComplexityPlot extends JFrame {
    public SortingTimeComplexityPlot() {
        setTitle("Sorting Algorithm Time Complexity");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create dataset
        XYSeries radixSortSeries = new XYSeries("Radix Sort");
        XYSeries insertionSortSeries = new XYSeries("Insertion Sort");
        XYSeries selectionSortSeries = new XYSeries("Selection Sort");

        int[] sizes = {10, 50, 100, 200, 500, 1000, 2000, 3000, 4000, 5000};

        for (int size : sizes) {
            int[] randomArray = generateRandomArray(size);

            long radixTime = measureTime(() -> SortingUtility.radixSort(randomArray));
            long insertionTime = measureTime(() -> SortingUtility.insertSort(randomArray));
            long selectionTime = measureTime(() -> SortingUtility.selectSort(randomArray));

            radixSortSeries.add(size, radixTime);
            insertionSortSeries.add(size, insertionTime);
            selectionSortSeries.add(size, selectionTime);
        }

        // Add series to dataset
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(radixSortSeries);
        dataset.addSeries(insertionSortSeries);
        dataset.addSeries(selectionSortSeries);

        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Sorting Algorithm Time Complexity",
                "Array Size",
                "Execution Time (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        // Display chart
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private static long measureTime(Runnable sortingAlgorithm) {
        long startTime = System.nanoTime();
        sortingAlgorithm.run();
        return (System.nanoTime() - startTime) / 1_000_000; // Convert to milliseconds
    }

    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }
}
