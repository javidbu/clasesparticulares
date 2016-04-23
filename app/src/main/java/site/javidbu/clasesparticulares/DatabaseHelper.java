package site.javidbu.clasesparticulares;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//TODO Cuando implemente métodos de escritura de la base de datos, tengo que usar transacciones
// http://developer.android.com/intl/es/reference/android/database/sqlite/SQLiteDatabase.html

/**
 * Created by Javi on 23/04/2016.
 * Helper for the internal --provisional-- database
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "database.db";
    public static final int DATABASE_VERSION = 1;


    // Table creation
    private static final String CREATE_SUBJECTS = "create table subjects (_id integer primary key autoincrement, name text not null);";
    private static final String CREATE_STUDENTS = "create table students (_id integer primary key autoincrement, name text not null, subject_id integer, price integer not null, email text, phone integer, address text, comments text, foreign key (subject_id) references subjects (_id));";
    private static final String CREATE_CLASSES = "create table classes (_id integer primary key autoincrement, student_id integer not null, date integer not null, duration integer not null, paid integer not null, comments text, foreign key (student_id) references students (_id))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SUBJECTS);
        db.execSQL(CREATE_STUDENTS);
        db.execSQL(CREATE_CLASSES);

        // Subject adding
        db.execSQL("insert into subjects (name) values ('Matemáticas');");
        db.execSQL("insert into subjects (name) values ('Inglés');");
        db.execSQL("insert into subjects (name) values ('Física');");
        db.execSQL("insert into subjects (name) values ('Química');");
        db.execSQL("insert into subjects (name) values ('Dibujo');");
        db.execSQL("insert into subjects (name) values ('Lengua');");
        db.execSQL("insert into subjects (name) values ('Historia');");
        db.execSQL("insert into subjects (name) values ('Filosofía');");
        db.execSQL("insert into subjects (name) values ('Francés');");
        db.execSQL("insert into subjects (name) values ('Otro');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists classes;");
        db.execSQL("drop table if exists students;");
        db.execSQL("drop table if exists subjects;");
        onCreate(db);
    }
}
