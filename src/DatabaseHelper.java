import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

class DataBaseHelper {
    String driverClass = "com.mysql.cj.jdbc.Driver";
    String mySqlUrl = "jdbc:mysql://localhost:3306/POC";
    String userName = "root";
    String passwordOfDatabase = "Imkrv@1066";
    String dateAndTimeFormat = "yyyy/MM/dd HH:mm:ss";
    String createTable = "create table audit(PathToTheFile varchar(100),SearchedWord varchar(45),DateAndTimeOfSearch varchar(45),result varchar(45),WordCount int,ErrorMessage varchar(100))";

    public void storingDataToDatabase(String pathToTheFile, String theWordSearched, String result, int wordCount, String errorMessage) throws SQLException {
        Connection connectionToDataBase = null;
        Statement st = null;

        DateTimeFormatter dateAndTimeFormater = DateTimeFormatter.ofPattern(this.dateAndTimeFormat);
        LocalDateTime now = LocalDateTime.now();
        String currentDateAndTime = dateAndTimeFormater.format(now);
        try {
            connectionToDataBase = DriverManager.getConnection(this.mySqlUrl, this.userName, this.passwordOfDatabase);
            st = connectionToDataBase.createStatement();
            DatabaseMetaData checkIfTableIsThere = connectionToDataBase.getMetaData();
            ResultSet tables = checkIfTableIsThere.getTables(null, null, "audit", null);
            if (tables.next()) {
                st.execute("INSERT INTO audit VALUES ('" + pathToTheFile + "','" + theWordSearched + "','" + currentDateAndTime + "','" + result + "'," + wordCount + ",'" + errorMessage + "')");
            } else {
                this.createingTable(pathToTheFile, theWordSearched, currentDateAndTime, result, wordCount, errorMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(connectionToDataBase).close();
        }
    }

    private void createingTable(String filepath, String searchedWord, String currentDateAndTime, String resultToDatabase, int totalNoOfWords, String errorMessage) throws SQLException {
        Connection connectionToDataBase = null;
        try {
            Class.forName(this.driverClass);
            connectionToDataBase = DriverManager.getConnection(this.mySqlUrl, this.userName, this.passwordOfDatabase);
            Statement st = connectionToDataBase.createStatement();
            st.execute(this.createTable);
            st.execute("INSERT INTO audit VALUES ('" + filepath + "','" + searchedWord + "','" + currentDateAndTime + "','" + resultToDatabase + "'," + totalNoOfWords + ",'" + errorMessage + "')");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Objects.requireNonNull(connectionToDataBase).close();
        }
    }
}


