package site.javidbu.clasesparticulares;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javi on 24/04/2016.
 * Data source for Students
 */
public class StudentDataSource {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public StudentDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Student createStudent(String name, long subject_id, float price, String email, long phone, String address, String comments) {
        ContentValues valores = new ContentValues();
        valores.put("name", name);
        valores.put("subject_id", subject_id);
        valores.put("price", price);
        valores.put("email", email);
        valores.put("phone", phone);
        valores.put("address", address);
        valores.put("comments", comments);
        long insertId = db.insert("students", null, valores);
        Cursor cursor = db.rawQuery("select students._id, students.name, students.subject_id, " +
                "students.price, students.email, students.phone, students.address, " +
                "students.comments, sum(classes.duration)*students.price as debt, " +
                "count(classes._id) as classes, subjects.name as subject " +
                " from students left join classes on students._id " +
                "= classes.student_id and classes.paid = 0 left join subjects on subjects._id = " +
                "students.subject_id where students._id = " + insertId +
                " group by students._id, students.name, students.subject_id, students.price, " +
                "students.email, students.phone, students.address, students.comments, subjects.name", null);
        cursor.moveToFirst();
        Student newStudent = cursorToStudent(cursor);
        cursor.close();
        return newStudent;
    }

    public void updateStudent(Long id, String name, long subject_id, float price, String email, long phone, String address, String comments) {
        ContentValues valores = new ContentValues();
        valores.put("name", name);
        valores.put("subject_id", subject_id);
        valores.put("price", price);
        valores.put("email", email);
        valores.put("phone", phone);
        valores.put("address", address);
        valores.put("comments", comments);
        db.update("students", valores, "_id = " + id, null);
    }

    public void deleteStudent(Student student) {
        long id = student.getId();
        db.delete("classes", "student_id = " + id, null);
        db.delete("students", "_id = " + id, null);
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select students._id, students.name, students.subject_id, " +
                "students.price, students.email, students.phone, students.address, " +
                "students.comments, sum(classes.duration)*students.price as debt, " +
                "sum(classes.duration) as classes, subjects.name as subject from students left join classes on students._id " +
                "= classes.student_id and classes.paid = 0 left join subjects on students.subject_id" +
                " = subjects._id group by students._id, students.name, " +
                "students.subject_id, students.price, students.email, students.phone, " +
                "students.address, students.comments, subjects.name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = cursorToStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
    }

    public Student getStudent(long id) {
        Cursor cursor = db.rawQuery("select students._id, students.name, students.subject_id, " +
                "students.price, students.email, students.phone, students.address, " +
                "students.comments, sum(classes.duration)*students.price as debt, " +
                "sum(classes.duration) as classes, subjects.name as subject " +
                "from students left join classes on students._id " +
                "= classes.student_id and classes.paid = 0 left join subjects on students.subject_id" +
                " = subjects._id where students._id = " + id +
                " group by students._id, students.name, " +
                "students.subject_id, students.price, students.email, students.phone, " +
                "students.address, students.comments, subjects.name", null);
        cursor.moveToFirst();
        Student student = cursorToStudent(cursor);
        cursor.close();
        return student;
    }

    public Student cursorToStudent(Cursor cursor) {
        Student student = new Student();
        student.setId(cursor.getLong(0));
        student.setName(cursor.getString(1));
        student.setSubject_id(cursor.getLong(2));
        student.setPrice(cursor.getFloat(3));
        student.setEmail(cursor.getString(4));
        student.setPhone(cursor.getLong(5));
        student.setAddress(cursor.getString(6));
        student.setComments(cursor.getString(7));
        student.setDebt(cursor.getInt(8));
        student.setClasses(cursor.getFloat(9));
        student.setSubject(cursor.getString(10));
        return student;
    }

    public List<Class> getAllStudentsClasses(long student_id) {
        List<Class> classes = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from classes where student_id = "
                + student_id + " order by date desc", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Class clase = cursorToClass(cursor);
            classes.add(clase);
            cursor.moveToNext();
        }
        cursor.close();
        return classes;
    }

    public List<Class> getAllStudentsUnpaidClasses(long student_id) {
        List<Class> classes = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from classes where paid = 0 and student_id = " +
                student_id + " order by date desc", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Class clase = cursorToClass(cursor);
            classes.add(clase);
            cursor.moveToNext();
        }
        cursor.close();
        return classes;
    }

    public Class createOrUpdateClass(Long id, Long student_id, Long date, float duration, Long paid, String comments) {
        ContentValues valores = new ContentValues();
        valores.put("student_id", student_id);
        valores.put("date", date);
        valores.put("duration", duration);
        valores.put("paid", paid);
        valores.put("comments", comments);
        if (id == 0L) {
            id = db.insert("classes", null, valores);
        } else {
            db.update("classes", valores, "_id = " + id, null);
        }
        Cursor cursor = db.rawQuery("select * from classes where _id = " + id, null);
        cursor.moveToFirst();
        Class newClass = cursorToClass(cursor);
        cursor.close();
        return newClass;
    }

    public Class cursorToClass(Cursor cursor) {
        Class clase = new Class();
        clase.setId(cursor.getLong(0));
        clase.setStudent_id(cursor.getLong(1));
        clase.setDate(cursor.getLong(2));
        clase.setDuration(cursor.getFloat(3));
        clase.setPaid(cursor.getLong(4));
        clase.setComments(cursor.getString(5));
        return clase;
    }
}
