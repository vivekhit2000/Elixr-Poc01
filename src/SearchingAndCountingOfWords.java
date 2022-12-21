import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;

/*
 *SearchingAndCountingOfWords is used for Search the user input and if the input is found in the file, it will count the words.
 */
class SearchingAndCountingOfWords implements Runnable {

    protected String inputFilePath;
    protected String userSearchInput;
    protected int userInputCount = 0;

    SearchingAndCountingOfWords(String userSearchInput, String inputfilepath) {
        this.userSearchInput = userSearchInput;
        this.inputFilePath = inputfilepath;
    }

    @Override
    public void run() {

        if (inputFilePath.endsWith("txt") || inputFilePath.endsWith("json")) {
            File file = new File(inputFilePath);
            if (file.exists()) {
                System.out.println("FileFound");
                countingofWords();
            } else {
                try {
                    SearchFileApplication.errorMessage(inputFilePath, userSearchInput);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public void countingofWords() {
        try {
            File filePathReader = new File(this.inputFilePath);
            FileReader readContentofFle = new FileReader(filePathReader);
            BufferedReader br = new BufferedReader(readContentofFle);
            String[] words = null;
            String inputForSplit;
            while ((inputForSplit = br.readLine()) != null) {
                words = inputForSplit.split("[.,!@#$%*()=/;:+_ ]");
                for (String word : words) {
                    if (word.equals(userSearchInput)) {
                        userInputCount++;
                    }
                }
            }
            SearchFileApplication.displayResult(this.inputFilePath,this.userSearchInput,userInputCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




