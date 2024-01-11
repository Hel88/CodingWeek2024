package eu.telecomnancy.codingweek.utils;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllBytes;


public class DataCalendarUtils {

    private final String filePath;

    private static DataCalendarUtils instance;

    private JSONObject data = null;

    private DataCalendarUtils() throws IOException {
        FileAccess fileAccess = new FileAccess();
        this.filePath = fileAccess.getPathOf("calendars.json");
        File file = new File(filePath);
        String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        data = new JSONObject(fileContent);
    }

    public static synchronized DataCalendarUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new DataCalendarUtils();
        }
        return instance;
    }

    public Calendar load(int id) throws IOException {
        JSONObject calendarObject = data.getJSONObject(String.valueOf(id));
        Calendar calendar = new Calendar(calendarObject.getString("titre"));
        calendar.setShortName(String.valueOf(id));
        String entries = calendarObject.getString("entries");
        if(entries.equals("")){
            return calendar;
        }
        String[] entriesArray = entries.split(",");
        for (String entryId : entriesArray) {
            DataEntryUtils dataEntryUtils = DataEntryUtils.getInstance();
            Entry<?> entry = dataEntryUtils.load(Integer.parseInt(entryId));
            calendar.addEntry(entry);
        }
        return calendar;
    }

    public int store(Calendar calendar) throws IOException {
        // Check if it exists
        try {
            int idCalendar = Integer.parseInt(calendar.getShortName());
            return save(idCalendar, calendar);
        }
        catch (NumberFormatException e) {
            int idCalendar = newID();
            return save(idCalendar, calendar);
        }
    }

    private int save(int idCalendar, Calendar calendar) throws IOException {
        JSONObject calendarObject = new JSONObject();
        calendarObject.put("titre", calendar.getName());
        StringBuilder entries = new StringBuilder();
        List<List<String>> liste = new ArrayList<>(new ArrayList<>());
        List<Entry<?>> toAdd = new ArrayList<>();
        for (Entry<?> entry : calendar.findEntries("")) {
            List<String> infos = new ArrayList<>();
            infos.add(entry.getTitle());
            infos.add(String.valueOf(entry.isFullDay()));
            infos.add(entry.getStartTime().toString());
            infos.add(entry.getEndTime().toString());
            infos.add(entry.getZoneId().toString());
            infos.add(String.valueOf(entry.isRecurring()));
            infos.add(entry.getRecurrenceRule());
            if (liste.contains(infos)) {
                continue;
            }
            liste.add(infos);
            toAdd.add(entry);
        }
        List<Entry<?>> reallyToAdd = new ArrayList<>();
        for (Entry<?> entry : toAdd) {
            Entry<?> minEntry = entry;
            for (Entry<?> entry2 : calendar.findEntries(entry.getTitle())) {
                if (String.valueOf(entry.isFullDay()).equals(String.valueOf(entry2.isFullDay())) && entry.getStartTime().equals(entry2.getStartTime()) && entry.getEndTime().equals(entry2.getEndTime()) && entry.getZoneId().equals(entry2.getZoneId()) && String.valueOf(entry.isRecurring()).equals(String.valueOf(entry2.isRecurring()))) {
                    if(entry.isRecurring()) {
                        if (!entry.getRecurrenceRule().equals(entry2.getRecurrenceRule())) {
                            continue;
                        }
                    }
                    if (minEntry.getStartDate().isAfter(entry2.getStartDate())) {
                        minEntry = entry2;
                    }
                }
            }
            if(!reallyToAdd.contains(minEntry)){
                reallyToAdd.add(minEntry);
            }
        }
        System.out.println("reallyToAdd : " + reallyToAdd);
        for (Entry<?> entry : reallyToAdd) {
            int id = DataEntryUtils.getInstance().store(entry);
            entries.append(String.valueOf(id)).append(",");
        }
        System.out.println(entries.toString());
        calendarObject.put("entries", entries.toString());
        data.put(String.valueOf(idCalendar), calendarObject);
        FileWriter file = new FileWriter(filePath);
        file.write(data.toString());
        file.flush();
        return idCalendar;
    }

    private int newID() {
        int id = 0;
        for (String key : data.keySet()) {
            if (Integer.parseInt(key) > id) {
                id = Integer.parseInt(key);
            }
        }
        return id + 1;
    }

    public void reload(Calendar calendar) throws IOException {
        JSONObject calendarObject = data.getJSONObject(calendar.getShortName());
        calendar.setName(calendarObject.getString("titre"));
        String entries = calendarObject.getString("entries");
        if(entries.equals("")){
            return;
        }
        String[] entriesArray = entries.split(",");
        for (String entryId : entriesArray) {
            DataEntryUtils dataEntryUtils = DataEntryUtils.getInstance();
            Entry<?> entry = dataEntryUtils.load(Integer.parseInt(entryId));
            calendar.addEntry(entry);
        }
    }

    public int getId(Calendar calendar) throws IOException {
        return this.store(calendar);
    }
}
