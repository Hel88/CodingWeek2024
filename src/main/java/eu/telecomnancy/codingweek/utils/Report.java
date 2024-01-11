package eu.telecomnancy.codingweek.utils;

public class Report {
    private String referent;
    private String message;
    private int timestamp;

    public Report() {}

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

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
