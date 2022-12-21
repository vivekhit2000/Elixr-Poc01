import java.sql.SQLException;

/*
 *SearchFileApplication is used to find the file path.
 * And use try catch to handle exception when user gives the wrong path.
 */
public class SearchFileApplication {
    public static void main(String[] args) throws Exception {
        String inputFilePath = "";
        String userSearchInput = "";
        if (args.length == Constants.argumentlength) {
            inputFilePath = args[0];
            userSearchInput = args[1];

            System.out.println("Processing...");
        }

        SearchingAndCountingOfWords rf = new SearchingAndCountingOfWords(userSearchInput, inputFilePath);
        Thread rfthread = new Thread(rf);
        rfthread.start();
    }

    public static void displayResult(String inputFilePath, String userSearchInput, int userInputCount) {
        DataBaseHelper object = new DataBaseHelper();
        if (userInputCount != 0) {
            try {
                object.storeDataToDatabase(inputFilePath, userSearchInput, "Success", userInputCount, " ");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //    System.out.println(totalWordCount);
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




