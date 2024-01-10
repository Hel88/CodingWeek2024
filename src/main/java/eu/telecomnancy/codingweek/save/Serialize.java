package eu.telecomnancy.codingweek.save;

import com.calendarfx.view.CalendarView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialize {
    public Serialize(){}

    public void save(CalendarView calendar){
        try (FileOutputStream fileOut = new FileOutputStream("object.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) { out.writeObject(calendar);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
