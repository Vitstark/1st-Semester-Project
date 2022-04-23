import javax.swing.*;

import org.jfree.data.xy.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;

import java.awt.*;
import java.util.Random;

public class Benchmarks {

    public static void main(String[] args) throws Exception {
        int numberOfMaps = 100;
        long start;
        long finish;
        
        TreeMap<Integer, Integer>[] treeMaps = new TreeMap[100];
        for (int i = 0; i < numberOfMaps; i++) {
            treeMaps[i] = new TreeMap<>();
        }

        for (int i = 0; i < 100; i++) {
            
        }

        XYSeries series = new XYSeries("Realization");
        
        XYDataset xyDataset = new XYSeriesCollection(ourSeries);

        JFreeChart chart = ChartFactory
                .createXYLineChart("Тест на добавление", "element", "time(nanoseconds)",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, false);
        JFrame frame = new JFrame("Statistic");

        frame.setLayout(new FlowLayout());

        frame.add(new ChartPanel(chart));

        frame.setSize(1200, 900);
        
        frame.show();
    }
}