import javax.swing.*;

import org.jfree.data.xy.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;

import java.awt.*;
import java.util.Random;

public class Benchmarks {

    public static void main(String[] args) throws Exception {
        int numberOfMaps = 100;
        TreeMap<Integer, Integer>[] maps = new TreeMap[numberOfMaps];
        for (int i = 0; i < numberOfMaps; i++) {
            maps[i] = new TreeMap<>();
        }

        XYSeries ourSeries = new XYSeries("Realization");

        long start;
        long finish;
        Random random = new Random();

        for (Integer i = 0; i < 100_000; i++) {
            int sub = 0;
            for (int j = 0; j < numberOfMaps; j++) {
                int number = random.nextInt();
                start = System.nanoTime();
                maps[j].put(number, i);
                finish = System.nanoTime();
                if (finish - start < 50_000) {
                    sub += finish - start;
                }
            }
            sub /= numberOfMaps;
            ourSeries.add(i.intValue(), sub);
        }
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
        frame.setVisible(true);
        frame.show();
    }
}