package eu.telecomnancy.codingweek.utils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        data = new JSONObject(fileContent);
    }

    public static synchronized DataReportUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new DataReportUtils();
        }
        return instance;
    }

    public void addReport(String username, String description, int date) throws IOException {
        int id = newId();
        JSONObject reportObject = new JSONObject();
        reportObject.put("referent", username);
        reportObject.put("message", description);
        reportObject.put("timestamp", date);
        data.put(Integer.toString(id), reportObject);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }
    }

    public ArrayList<Report> getReports() throws IOException {
        ArrayList<Report> reports = new ArrayList<Report>();
        for (String key : data.keySet()) {
            JSONObject reportObject = data.getJSONObject(key);
            String referent = reportObject.getString("referent");
            String message = reportObject.getString("message");
            int timestamp = reportObject.getInt("timestamp");
            Report report = new Report();
            report.setReferent(referent);
            report.setMessage(message);
            report.setTimestamp(timestamp);
            reports.add(report);
        }
        return reports;
    }

    public ArrayList<Report> getReportsByReferent(String referent) throws IOException {
        ArrayList<Report> reports = new ArrayList<Report>();
        for (String key : data.keySet()) {
            JSONObject reportObject = data.getJSONObject(key);
            String referentReport = reportObject.getString("referent");
            if (referentReport.equals(referent)) {
                String message = reportObject.getString("message");
                int timestamp = reportObject.getInt("timestamp");
                Report report = new Report();
                report.setReferent(referent);
                report.setMessage(message);
                report.setTimestamp(timestamp);
                reports.add(report);
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
}
