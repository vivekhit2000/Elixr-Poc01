import java.sql.*;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

class DataBaseHelper {
    public void storeDataToDatabase(String pathOfTheFile, String userSearchedWord, String result, int repetationOfWordCount, String errorMessage) throws SQLException {
        Connection connectionToDataBase = null;
        Statement st = null;
        DateTimeFormatter dateAndTimeFormater = DateTimeFormatter.ofPattern(Constants.DATE_AND_TIME_FORMAT);
        LocalDateTime now = LocalDateTime.now();
        String currentDateAndTime = dateAndTimeFormater.format(now);
        DataBaseHelper object = new DataBaseHelper();
        try {
            connectionToDataBase = object.connectToDatabase();
            st = connectionToDataBase.createStatement();
            DatabaseMetaData checkIfTableIsThere = connectionToDataBase.getMetaData();
            ResultSet tables = checkIfTableIsThere.getTables(null, null, Constants.AUDIT, null);
            if (tables.next()) {
                String query = MessageFormat.format("INSERT INTO AUDIT VALUES ({0},{1},{2},{3},{4},{5})", "'" + pathOfTheFile + "'", "'" + userSearchedWord + "'", "'" + currentDateAndTime + "'", "'" + result + "'", "'" + repetationOfWordCount + "'", "'" + errorMessage + "'");
                st.execute(query);
            } else {
                this.createTableAndInsertDataToDatabase(pathOfTheFile, userSearchedWord, currentDateAndTime, result, repetationOfWordCount, errorMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(connectionToDataBase).close();
        }
    }

    private void createTableAndInsertDataToDatabase(String pathOfTheFile, String userSearchedWord, String currentDateAndTime, String resultToDatabase, int totalNoOfWords, String errorMessage) throws SQLException {
        Connection connectionToDataBase = connectToDatabase();
        try {
            Statement st = connectionToDataBase.createStatement();
            st.execute(Constants.CREATE_TABLE);
            String query = MessageFormat.format("INSERT INTO audit VALUES ({0},{1},{2},{3},{4},{5})", "'" + pathOfTheFile + "'", "'" + userSearchedWord + "'", "'" + currentDateAndTime + "'", "'" + resultToDatabase + "'", "'" + totalNoOfWords + "'", "'" + errorMessage + "'");
            st.execute(query);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Objects.requireNonNull(connectionToDataBase).close();
        }
    }

    private Connection connectToDatabase() {
        Connection connectionToDataBase = null;
        try {
            Class.forName(Constants.DRIVER_CLASS);
            connectionToDataBase = DriverManager.getConnection(Constants.MY_SQL_URL, Constants.USER_NAME, Constants.PASSWORD_OF_DATABASE);
            return connectionToDataBase;
        } catch (Exception e) {
            e.printStackTrace();
            return connectionToDataBase;
        }
    }
}