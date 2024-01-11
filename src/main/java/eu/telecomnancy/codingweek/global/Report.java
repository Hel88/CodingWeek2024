package eu.telecomnancy.codingweek.global;

public class Report {
    private int id;
    private String referent;
    private String message;
    private String time;

    public Report() {}

    public Report(String referent, String message, String time, int id) {
        this.referent = referent;
        this.message = message;
        this.time = time;
    }

    public String getReferent() {
        return referent;
    }

    public void setReferent(String referent) {
        this.referent = referent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }
}
