package bg.tu_sofia.pmu.project.testsystem.utils;

/**
 * Created by Stefan Chuklev on 8.6.2016 г..
 */
public class User {
    public static enum UserType {
        ADMIN, STUDENT
    }

    public static User createStudentUser(int userID, String username, String password) {
        return new User(userID, username, password, UserType.STUDENT);
    }

    public static User createAdminUser(int userID,String username, String password) {
        return new User(userID, username, password, UserType.ADMIN);
    }

    private int userID = 0;
    private String username = null;
    private String password = null;
    private UserType userType = null;

    private User(int userID, String username, String password, UserType userType) {
        this.userID = userID;
        this.username = username;
        this.userType = userType;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getPassword() { return password; }

    public int getUserID() {
        return userID;
    }

}
