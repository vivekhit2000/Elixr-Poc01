import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
 *SearchingAndCountingOfWords is used for Search the user input and if the input is found in the file, it will count the words.
 */
class SearchingAndCountingOfWords implements Runnable {

    private final String inputFilePath;
    protected String userSearchInput;
    protected int userInputCount = 0;

    SearchingAndCountingOfWords(String userSearchInput, String inputFilePath) {
        this.userSearchInput = userSearchInput;
        this.inputFilePath = inputFilePath;

    }

    @Override
    public void run() {
        try {
            String[] words = null;
            File filePathReader = new File(inputFilePath);
            FileReader readContentofFle = new FileReader(filePathReader);
            BufferedReader br = new BufferedReader(readContentofFle);
            String inputForSplit;


            while ((inputForSplit = br.readLine()) != null) {
                words = inputForSplit.split("[.,!@#$%*()=/;:+_ ]");
                for (String word : words) {
                    if (word.equals(userSearchInput)) {
                        userInputCount++;
                    }
                }
            }
            DataBaseHelper object = new DataBaseHelper();
            if (userInputCount != 0) {
                object.storeDataToDatabase(inputFilePath, userSearchInput, "Success", userInputCount, " ");
                //    System.out.println(totalWordCount);
                System.out.println("The given word is present in file are " + userInputCount + " times");
            } else {
                object.storeDataToDatabase(inputFilePath, userSearchInput, "error", userInputCount, "word not found");
                System.out.println("The given word is not present in the file");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



