import javax.swing.*;

import org.jfree.data.xy.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.TreeMap;

public class Benchmarks {

    public static void main(String[] args) throws Exception {
        int NUMBER_OF_FILES = 100;
        int NUMBER_OF_INTEGERS = 100_000;

        long start;
        long finish;
        long [] timeAverage = new long[NUMBER_OF_INTEGERS];
        Path setsPath = Path.of("datasets/5");
        TreeMap<Integer, Integer> tree;

        List<Integer> list;

        for (int i = 1; i < NUMBER_OF_FILES + 1; i++) {
            tree = new TreeMap<>();
            list = Files
                    .lines(Path.of(setsPath + "/" + i + ".txt"))
                    .map(x -> Integer.parseInt(x))
                    .toList();
            for (int j = 0; j < NUMBER_OF_INTEGERS; j++) {
                Integer key = list.get(j);
                start = System.nanoTime();
                tree.put(key, null);
                finish = System.nanoTime();
                if (finish - start < 20000) {
                    timeAverage[j] += (finish - start);
                }
            }

        }

        XYSeries series = new XYSeries("TreeMap");

        for (int i = 5; i < NUMBER_OF_INTEGERS; i++) {
            timeAverage[i] /= NUMBER_OF_FILES;
            series.add(i, timeAverage[i]);
        }
        
        XYDataset xyDataset = new XYSeriesCollection(series);

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