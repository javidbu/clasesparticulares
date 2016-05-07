package site.javidbu.clasesparticulares;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Javi on 02/05/2016.
 * Class that represent classes (...)
 */
public class Class {
    private float duration;
    private long id, student_id, paid;
    private String comments;
    private Calendar printable_date;

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    public void setDate(long date) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTimeInMillis(date*1000L);
        this.printable_date = c;
    }

    public void setPaid(long paid) {
        this.paid = paid;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public float getDuration() {

        return duration;
    }

    public long getId() {
        return id;
    }

    public long getStudent_id() {
        return student_id;
    }

    public long getPaid() {
        return paid;
    }

    public String getComments() {
        return comments;
    }

    public Calendar getPrintable_date() {
        return printable_date;
    }
}
