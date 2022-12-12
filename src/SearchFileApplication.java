import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/*
 *SearchFileApplication is used to find the file path.
 * And use try catch to handle exception when user gives the wrong path.
 */
public class SearchFileApplication {
    protected static String inputFilePath, storingFileContent;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path");

        inputFilePath = sc.next();
        System.out.println("Processing");
        try {
            Path filepath = Path.of(SearchFileApplication.inputFilePath);
            storingFileContent = Files.readAllLines(filepath).get(0);

            if (storingFileContent != null) {
                System.out.println("FileFound");
                SearchingAndCountingOfWords rf = new SearchingAndCountingOfWords();
                rf.run();
            }
            else {
                System.out.println("Content is not there");
            }
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("Path is not correct You need to check the given path");
        }


    }
}
