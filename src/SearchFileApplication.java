import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 *SearchFileApplication is used to find the file path.
 * And use try catch to handle exception when user gives the wrong path.
 */

public class SearchFileApplication {
    public static void main(String[] args) throws Exception {
        String inputFilePath = "";
        String userSearchInput = "";
        if (args.length == Constants.ARGUMENT_LENGTH) {
            inputFilePath = args[0];
            userSearchInput = args[1];
            System.out.println("Processing...");
        }
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Future<Integer> welcomeChildThread = threadPool.submit(new SearchingAndCountingOfWords(userSearchInput, inputFilePath));
        int userInputCount = welcomeChildThread.get();
        displayResult(inputFilePath, userSearchInput, userInputCount);
        threadPool.close();
    }

    public static void displayResult(String inputFilePath, String userSearchInput, int userInputCount) {
        DataBaseHelper object = new DataBaseHelper();
        if (userInputCount != 0) {
            try {
                object.storeDataToDatabase(inputFilePath, userSearchInput, "Success", userInputCount, " ");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("The given word is present in file are " + userInputCount + " times");
        } else {
            try {
                object.storeDataToDatabase(inputFilePath, userSearchInput, "error", userInputCount, "word not found");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("The given word is not present in the file");
        }
    }

    public static void errorMessage(String inputFilePath, String userSearchInput) throws SQLException {
        DataBaseHelper object = new DataBaseHelper();
        object.storeDataToDatabase(inputFilePath, userSearchInput, "error", 0, "file not found");
        System.out.println("Error arguments..! ");
    }
}




