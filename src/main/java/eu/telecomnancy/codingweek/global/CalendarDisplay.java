package eu.telecomnancy.codingweek.global;

import com.calendarfx.model.Calendar;
import com.calendarfx.view.DateControl;
import eu.telecomnancy.codingweek.controllers.SceneController;
import eu.telecomnancy.codingweek.utils.DataCalendarUtils;
import javafx.util.Callback;

import java.io.IOException;

public class CalendarDisplay {

    private final SceneController sc;

    public CalendarDisplay(SceneController sceneController) {
        this.sc = sceneController;
    }

    public void calendarSave() throws IOException {
        if (sc.getCurrentCalendarList() != null) {
            DataCalendarUtils dataCalendarUtils = DataCalendarUtils.getInstance();
            for (Calendar calendar : sc.getCurrentCalendarList()) {
                dataCalendarUtils.store(calendar);
            }
        }
    }

    public void calendarSwitchPreparation() throws IOException {
        calendarSave();

        sc.getMyCalendarSource().getCalendars().clear();
        sc.getCalendarView().getCalendarSources().clear();
        sc.getCalendarView().setDefaultCalendarProvider(new Callback<DateControl, Calendar>() {
            @Override
            public Calendar call(DateControl param) {
                return null;
            }
        });
    }

    public void calendarSwitchSetCurrentCalendarToDefault() {
        sc.setDefaultCalendar(sc.getCurrentCalendar());
        sc.getCalendarView().setDefaultCalendarProvider(new Callback<DateControl, Calendar>() {
            @Override
            public Calendar call(DateControl param) {
                return sc.getDefaultCalendar();
            }
        });
    }

    public void calendarSwitchAddCalendar(int id, boolean save) throws IOException {
        Calendar calendar = DataCalendarUtils.getInstance().load(id);
        sc.getCalendarList().add(id);

        sc.setCurrentCalendar(calendar);
        DataCalendarUtils.getInstance().reload(sc.getCurrentCalendar());

        if (save) {
            sc.getCurrentCalendarList().add(sc.getCurrentCalendar());
        }

        sc.getMyCalendarSource().getCalendars().addAll(sc.getCurrentCalendar());
    }

    public void calendarSwitchAddCalendarWithStyle(int id, Calendar.Style style, boolean save) throws IOException {
        Calendar calendar = DataCalendarUtils.getInstance().load(id);
        calendar.setStyle(style);
        sc.getCalendarList().add(id);

        sc.setCurrentCalendar(calendar);
        DataCalendarUtils.getInstance().reload(sc.getCurrentCalendar());

        if (save) {
            sc.getCurrentCalendarList().add(sc.getCurrentCalendar());
        }

        sc.getMyCalendarSource().getCalendars().addAll(sc.getCurrentCalendar());
    }
}
