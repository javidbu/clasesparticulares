package site.javidbu.clasesparticulares;

/**
 * Created by Javi on 02/05/2016.
 * Class that represent classes (...)
 */
public class Class {
    private float duration;
    private long id, student_id, date, paid;
    private String comments;

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
        this.date = date;
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

    public long getDate() {
        return date;
    }

    public long getPaid() {
        return paid;
    }

    public String getComments() {
        return comments;
    }
}
