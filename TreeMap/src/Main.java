import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        writeDataSets();
    }

    public static void writeDataSets() throws IOException{
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