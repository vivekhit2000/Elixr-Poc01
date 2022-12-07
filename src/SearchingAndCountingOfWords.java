import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/*
*SearchingAndCountingOfWords is used for Search the user input and if the input is found in the file, it will count the words.
 */
class SearchingAndCountingOfWords implements Runnable {

    protected static String userInfo;
    @Override
    public void run() {
        System.out.println("Enter word you want to search");
        Scanner sc = new Scanner(System.in);
        userInfo = sc.next();


        try {
            String[] words = null ;
            File f1 = new File(SearchFileApplication.filePath);
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            String input;

            int count = 0;
            while ((input = br.readLine()) != null)
            {
                words = input.split("[.,!@#$%*()=/;:+_ ]");
                for (String word : words) {
                    if (word.equals(userInfo))
                    {
                        count++;
                    }
                }
            }
            if (count != 0)
            {
                System.out.println("The given word is present in file are " + count + " times");
            } else {
                System.out.println("The given word is not present in the file");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
