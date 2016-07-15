package site.javidbu.clasesparticulares;

/**
 * Created by Javi on 02/05/2016.
 * Class that represent homework
 */
public class Homework {
    private long id, student_id, done;
    private String name;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    public void setDone(long done) {
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getStudent_id() {
        return student_id;
    }

    public long getDone() {
        return done;
    }
}
