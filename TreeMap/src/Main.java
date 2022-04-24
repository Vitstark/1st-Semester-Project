import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        Random random = new Random();
        /*
        tree.put(2, 0);
        tree.put(-2, 1);
        tree.put(13, 2);
        tree.put(3, 3);
        tree.put(11, 4);
        tree.put(12, 5);
        tree.put(-6, 6);
        tree.put(6, 7);
        tree.put(1, 8);
        tree.put(9, 9);
        */
        for (int i = 0; i < 10_000; i++) {
            tree.put(random.nextInt(Integer.MAX_VALUE), null);
        }

        List<Integer> list = tree.getSortedList()
                .stream()
                .map(x -> x.getKey())
                .toList();

        System.out.println(tree);

        for (Integer elem : list) {
            tree.remove(elem);
        }

        System.out.println(tree);

    }

    public static void writeDataSets() throws IOException {
        Path dirPath = Path.of("datasets");
        DataSetCreator dataSetCreator = new DataSetCreator(Integer.MAX_VALUE);
        for (int i = 2; i < 7; i++) {
            Path pathOfSetsDirectory = Path.of(dirPath + "/" + i);
            Files.createDirectory(pathOfSetsDirectory);
            dataSetCreator.setsCreate(pathOfSetsDirectory, i, 100);
        }
    }
}

class DataSetCreator {

    private int rangeOfValues;

    public DataSetCreator(int rangeOfValues) {
        this.rangeOfValues = rangeOfValues;
    }

    public void setsCreate(Path outPath, int deg, int numberOfSets) throws IOException {
        Random numberGenerator = new Random();
        for (int i = 1; i < numberOfSets + 1; i++) {
            Path pathOfFile = Path.of(outPath.toString() + "/" + i + ".txt");
            Files.createFile(pathOfFile);
            try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(pathOfFile.toString()))) {
                for (int j = 0; j < Math.pow(10, deg); j++) {
                    printWriter.print(numberGenerator.nextInt(rangeOfValues));
                    printWriter.println();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}