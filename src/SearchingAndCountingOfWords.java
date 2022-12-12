import java.io.*;

/*
 *SearchingAndCountingOfWords is used for Search the user input and if the input is found in the file, it will count the words.
 */
class SearchingAndCountingOfWords implements Runnable {
    protected String userSearchInput;
    private final String inputFilePath;
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

            int userInputCount = 0;
            while ((inputForSplit = br.readLine()) != null) {
                words = inputForSplit.split("[.,!@#$%*()=/;:+_ ]");
                for (String word : words) {
                    if (word.equals(userSearchInput)) {
                        userInputCount++;
                    }
                }
            }
            if (userInputCount != 0)
            {
                System.out.println("The given word is present in file are " + userInputCount + " times");
            }

            else {
                System.out.println("The given word is not present in the file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



