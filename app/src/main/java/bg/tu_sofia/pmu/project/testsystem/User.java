package bg.tu_sofia.pmu.project.testsystem;

/**
 * Created by Altair on 8.6.2016 г..
 */
public class User {
    public static enum UserType {
        ADMIN, STUDENT
    }

    public static User createStudentUser(String username) {
        return new User(username, UserType.STUDENT);
    }

    public static User createAdminUser(String username) {
        return new User(username, UserType.ADMIN);
    }

    private String username = null;
    private UserType userType = null;

    private User(String username, UserType userType) {
        this.username = username;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
