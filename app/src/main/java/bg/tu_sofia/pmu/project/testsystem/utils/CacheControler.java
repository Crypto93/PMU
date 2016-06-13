package bg.tu_sofia.pmu.project.testsystem.utils;

/**
 * Created by Stefan Chuklev on 12.6.2016 г..
 */
public class CacheControler {
    private static CacheControler ourInstance = new CacheControler();

    private User user = null;

    public static synchronized CacheControler getInstance() {
        return ourInstance;
    }

    private CacheControler() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
