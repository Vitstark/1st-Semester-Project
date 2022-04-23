import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

    }

    public static void writeDataSets() throws IOException{
        Path dirPath = Path.of("datasets");
        DataSetCreator dataSetCreator = new DataSetCreator(1_000_000_000);
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
            try (OutputStream outputStream = Files.newOutputStream(pathOfFile)) {
                for (int j = 0; j < Math.pow(10, deg); j++) {
                    outputStream.write(numberGenerator.nextInt(rangeOfValues));
                    outputStream.write('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}