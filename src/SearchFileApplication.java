import java.io.File;

/*
 *SearchFileApplication is used to find the file path.
 * And use try catch to handle exception when user gives the wrong path.
 */
public class SearchFileApplication {
    public static void main(String[] args) throws Exception {
        String inputFilePath = "";
        String userSearchInput = "";
        if (args.length == 2) {
            inputFilePath = args[0];
            userSearchInput = args[1];

            System.out.println("Processing...");
        }
        
        if (inputFilePath.endsWith("txt") || inputFilePath.endsWith("json")) {
            File file = new File(inputFilePath);
            if (file.exists()) {
                System.out.println("FileFound");
                SearchingAndCountingOfWords rf = new SearchingAndCountingOfWords(userSearchInput, inputFilePath);
                rf.run();
            } else {
                DataBaseHelper object = new DataBaseHelper();
                object.storeDataToDatabase(inputFilePath, userSearchInput, "error", 0, "file not found");
                System.out.println("Error arguments..! ");
            }
        } else {
            System.out.println("Error! your file is not in txt or json format");
        }


    }
}


