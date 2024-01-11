package eu.telecomnancy.codingweek.utils;

import com.calendarfx.model.Entry;
import eu.telecomnancy.codingweek.global.FileAccess;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import static java.nio.file.Files.readAllBytes;

public class DataEntryUtils {

    private final String filePath;

    private static DataEntryUtils instance;

    private JSONObject data = null;

    private DataEntryUtils() throws IOException {
        FileAccess fileAccess = new FileAccess();
        this.filePath = fileAccess.getPathOf("entries.json");
        File file = new File(filePath);
        String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        data = new JSONObject(fileContent);
    }

    public static synchronized DataEntryUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new DataEntryUtils();
        }
        return instance;
    }

    public void store(List<Entry<?>> liste) throws IOException {
        for (Entry<?> entry : liste) {
            store(entry);
        }
        FileWriter file = new FileWriter(filePath);
        file.write(data.toString());
        file.flush();
    }

    public int store(Entry<?> entry) throws IOException {
        int idExistant = checkIfExist((Entry<String>) entry);
        if(idExistant != -1){
            return idExistant;
        }
        JSONObject entryObject = new JSONObject();
        entryObject.put("titre", entry.getTitle());
        entryObject.put("id", entry.getId());
        entryObject.put("fullday", entry.isFullDay());
        entryObject.put("startdate", entry.getStartDate().toString());
        entryObject.put("enddate", entry.getEndDate().toString());
        entryObject.put("starttime", entry.getStartTime().toString());
        entryObject.put("endtime", entry.getEndTime().toString());
        entryObject.put("zoneid", entry.getZoneId().toString());
        entryObject.put("recurring", entry.isRecurring());
        entryObject.put("rrule", entry.getRecurrenceRule());
        entryObject.put("recurrenceid", entry.getRecurrenceId());
        String id = newID();
        data.put(id, entryObject);
        FileWriter file = new FileWriter(filePath);
        file.write(data.toString());
        file.flush();
        return Integer.parseInt(id);

    }

    private String newID(){
        int id = 0;
        for (String key : data.keySet()) {
            int currentId = Integer.parseInt(key);
            if (currentId > id) {
                id = currentId;
            }
        }
        return String.valueOf(id + 1);
    }

    public Entry<String> load(int numero) throws IOException {
        File file = new File(filePath);
        String fileContent = new String(readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        data = new JSONObject(fileContent);
        JSONObject entryObject = data.getJSONObject(String.valueOf(numero));
        Entry<String> entry = new Entry<>(entryObject.getString("titre"));
        entry.setId(String.valueOf(entryObject.getInt("id")));
        entry.setFullDay(entryObject.getBoolean("fullday"));
        String startDate = entryObject.getString("startdate");
        LocalDate localDate = LocalDate.parse(startDate);
        String startTime = entryObject.getString("starttime");
        LocalTime localTime = LocalTime.parse(startTime);
        String endDate = entryObject.getString("enddate");
        LocalDate localDate2 = LocalDate.parse(endDate);
        String endTime = entryObject.getString("endtime");
        LocalTime localTime2 = LocalTime.parse(endTime);
        ZoneId zoneId = ZoneId.of(entryObject.getString("zoneid"));
        entry.setInterval(localDate, localTime, localDate2, localTime2, zoneId);
        if (entryObject.getBoolean("recurring")) {
            entry.setRecurrenceRule(entryObject.getString("rrule"));
        }
        return entry;

    }

    private int checkIfExist(Entry<String> entry) {
        // Check if there is already an entry with the same characteristics
        for (String key : data.keySet()) {
            JSONObject entryObject = data.getJSONObject(key);
            if (entryObject.getString("titre").equals(entry.getTitle()) && entryObject.getString("startdate").equals(entry.getStartDate().toString()) && entryObject.getString("enddate").equals(entry.getEndDate().toString()) && entryObject.getString("starttime").equals(entry.getStartTime().toString()) && entryObject.getString("endtime").equals(entry.getEndTime().toString()) && entryObject.getString("zoneid").equals(entry.getZoneId().toString())) {
                return Integer.parseInt(key);
            }
        }
        return -1;
    }

}
