import javax.swing.*;
import org.jfree.data.xy.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;

import java.util.Random;

public class Benchmarks {
    public static void main(String[] args) throws Exception {
        TreeMap<Integer, Integer>[] maps = new TreeMap[100];
        for (int i = 0; i < maps.length; i++) {
            maps[i] = new TreeMap<>();
        }

        XYSeries series = new XYSeries("TreeTest");

        long start;
        long finish;
        Random random = new Random();

        for (Integer i = 0; i < 100_000; i++){
            int sub = 0;
            for (int j = 0; j < maps.length; j++) {
                start = System.nanoTime();
                maps[j].put(i, i);
                finish = System.nanoTime();
                if (finish - start < 100_000) {
                    sub += finish - start;
                }
            }
            sub /= maps.length;
            series.add(i.intValue(), sub);
        }
        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("Тест на добавление", "element", "time(nanoseconds)",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        JFrame frame =
                new JFrame("MinimalStaticChart");
        // Помещаем график на фрейм
        frame.getContentPane()
                .add(new ChartPanel(chart));
        frame.setSize(1200,700);
        frame.show();
    }
}
