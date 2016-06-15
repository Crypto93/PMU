package bg.tu_sofia.pmu.project.testsystem.persistence.model;

/**
 * Created by Stefan Chuklev on 15.6.2016 г..
 */
public class Result {

    private String user;
    private String category;
    private int correctAnswers;
    private int wrongAnswers;
    private long dateTaken;



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public long getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(long dateTaken) {
        this.dateTaken = dateTaken;
    }

    public Result(String user, String category, int correctAnswers, int wrongAnswers, long dateTaken) {

        this.user = user;
        this.category = category;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
        this.dateTaken = dateTaken;
    }
}
