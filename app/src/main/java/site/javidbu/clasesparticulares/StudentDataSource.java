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
                "count(classes._id) as classes from students left join classes on students._id " +
                "= classes.student_id and classes.paid = 0 where students._id = " + insertId +
                " group by students._id, students.name, students.subject_id, students.price, " +
                "students.email, students.phone, students.address, students.comments", null);
        cursor.moveToFirst();
        Student newStudent = cursorToStudent(cursor);
        cursor.close();
        return newStudent;
    }

    public void deleteStudent(Student student) {
        long id = student.getId();
        db.delete("students", "_id = " + id, null);
        db.delete("classes", "student_id = " + id, null);
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select students._id, students.name, students.subject_id, " +
                "students.price, students.email, students.phone, students.address, " +
                "students.comments, sum(classes.duration)*students.price as debt, " +
                "count(classes._id) as classes from students left join classes on students._id " +
                "= classes.student_id and classes.paid = 0 group by students._id, students.name, " +
                "students.subject_id, students.price, students.email, students.phone, " +
                "students.address, students.comments", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = cursorToStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
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
        student.setClasses(cursor.getInt(9));
        return student;
    }
}
