package bg.tu_sofia.pmu.project.testsystem;

/**
 * Created by Stefan Chuklev on 8.6.2016 г..
 */
public class User {
    public static enum UserType {
        ADMIN, STUDENT
    }

    public static User createStudentUser(String username, String password) {
        return new User(username, password, UserType.STUDENT);
    }

    public static User createAdminUser(String username, String password) {
        return new User(username, password, UserType.ADMIN);
    }

    private String username = null;
    private String password = null;
    private UserType userType = null;

    private User(String username, String password, UserType userType) {
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

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
