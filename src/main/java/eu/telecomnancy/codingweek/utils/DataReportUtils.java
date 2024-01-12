package eu.telecomnancy.codingweek.utils;

import eu.telecomnancy.codingweek.global.FileAccess;
import eu.telecomnancy.codingweek.global.Report;
import eu.telecomnancy.codingweek.global.User;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class DataReportUtils {

    private final String filePath;
    private static DataReportUtils instance;
    private final JSONObject data;

    private DataReportUtils() throws IOException {
        FileAccess fileAccess = new FileAccess();
        this.filePath = fileAccess.getPathOf("report.json");
        File file = new File(filePath);
        String fileContent = Files.readString(file.toPath());
        data = new JSONObject(fileContent);
    }

    public static synchronized DataReportUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new DataReportUtils();
        }
        return instance;
    }

    public void addReport(String username, String description, String date) throws IOException {
        int id = newId();
        JSONObject reportObject = new JSONObject();
        reportObject.put("referent", username);
        reportObject.put("message", description);
        reportObject.put("time", date);
        data.put(Integer.toString(id), reportObject);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }
    }

    public ArrayList<Report> getReports(User user) throws IOException {
        try {
            boolean isAdmin = user.getIsAdmin();
            if (!isAdmin) {
                return new ArrayList<Report>();
            }
        } catch (Exception e) {
            return new ArrayList<Report>();
        }
        ArrayList<Report> reports = new ArrayList<Report>();
        for (String key : data.keySet()) {
            JSONObject reportObject = data.getJSONObject(key);
            String referent = reportObject.getString("referent");
            String message = reportObject.getString("message");
            String time = reportObject.getString("time");
            reports.add(new Report(referent, message, time, Integer.parseInt(key)));
        }
        return reports;
    }

    public ArrayList<Report> getReportsByReferent(String referent) {
        ArrayList<Report> reports = new ArrayList<Report>();
        for (String key : data.keySet()) {
            JSONObject reportObject = data.getJSONObject(key);
            String referentReport = reportObject.getString("referent");
            if (referentReport.equals(referent)) {
                String message = reportObject.getString("message");
                String time = reportObject.getString("time");
                reports.add(new Report(referent, message, time, Integer.parseInt(key)));
            }
        }
        return reports;
    }

    private int newId() {
        int id = 0;
        for (String key : data.keySet()) {
            int keyInt = Integer.parseInt(key);
            if (keyInt > id) {
                id = keyInt;
            }
        }
        return id + 1;
    }

    public String getFilePath() {
        return filePath;
    }
}
