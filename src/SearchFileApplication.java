import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
*SearchFileApplication is used to find the file path.
* And use try catch to handle exception when user gives the wrong path.
 */
public class SearchFileApplication {
    protected static String filePath;

    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path");

        filePath = sc.next();
        System.out.println("Processing");                               

        try {

            File f1 = new File(filePath);
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            String storingFileContent = br.readLine();
            if (storingFileContent != null)
            {
                System.out.println("FileFound");
                SearchingAndCountingOfWords rf = new SearchingAndCountingOfWords();
                rf.run();
            }
            else
            {
                System.out.println("Content is not there");
            }
        }
        catch (IOException e) {
           // e.printStackTrace();
            System.out.println("Path is not correct You need to check the given path");
        }
    }
}




