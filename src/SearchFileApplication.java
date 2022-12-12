import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
 *SearchFileApplication is used to find the file path.
 * And use try catch to handle exception when user gives the wrong path.
 */
public class SearchFileApplication {
    public static void main(String[] args) throws Exception {
        String inputFilePath = args[0];
        String userSearchInput = args[1];
        System.out.println("Processing...");
        try {
            Path filepath = Path.of(inputFilePath);
            String storingFileContent = Files.readAllLines(filepath).get(0);

            if (storingFileContent != null) {
                System.out.println("FileFound");
                SearchingAndCountingOfWords rf = new SearchingAndCountingOfWords(userSearchInput, inputFilePath);
                rf.run();
            } else {
                System.out.println("Content is not there");
            }
        } catch (IOException e) {
            System.out.println("Path is Wrong, You need to check your path");
        }
    }
}


