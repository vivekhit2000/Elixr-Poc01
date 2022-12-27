public class Constants {
    public static final String SPECIALCHARACTER = "[.,!@#$%*()=/;:+_ ]";
    public static final String AUDIT ="audit";
    public static final int ARGUMENT_LENGTH = 2;
    public static final String TXT_EXTENSION = "txt";
    public static final String JSON_EXTENSION = "json";
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String MY_SQL_URL = "jdbc:mysql://localhost:3306/POC";
    public static final String USER_NAME = "root";
    public static final String PASSWORD_OF_DATABASE = "Imkrv@1066";
    public static final String DATE_AND_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String CREATE_TABLE = "create table audit(PathToTheFile varchar(100) , SearchedWord varchar(45) , DateAndTimeOfSearch varchar(45) , result varchar(45) , WordCount int , ErrorMessage varchar(100))";
}
