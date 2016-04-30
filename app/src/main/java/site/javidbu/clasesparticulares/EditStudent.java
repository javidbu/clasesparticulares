package site.javidbu.clasesparticulares;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class EditStudent extends AppCompatActivity {

    private EditText edName;
    private Spinner edSubject;
    private EditText edPrice;
    private EditText edEmail;
    private EditText edPhone;
    private EditText edAddress;
    private EditText edComments;
    private Long student_id;
    private StudentDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        datasource = new StudentDataSource(this);
        student_id = getIntent().getLongExtra("student_id", 0L);
        edName = (EditText) findViewById(R.id.edit_name);
        edSubject = (Spinner) findViewById(R.id.edit_subject);
        edPrice = (EditText) findViewById(R.id.edit_price);
        edEmail = (EditText) findViewById(R.id.edit_email);
        edPhone = (EditText) findViewById(R.id.edit_phone);
        edAddress = (EditText) findViewById(R.id.edit_address);
        edComments = (EditText) findViewById(R.id.edit_comments);

        DatabaseHelper dbhelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from subjects", null);
        List<String> subjects = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                subjects.add(c.getString(1));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjects);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edSubject.setAdapter(spinner_adapter);

        if (student_id > 0L) {
            datasource.open();
            Student student = datasource.getStudent(student_id);
            datasource.close();
            edName.setText(student.getName());
            edSubject.setSelection((int)student.getSubject_id()-1);
            edPrice.setText(String.valueOf(student.getPrice()));
            edEmail.setText(student.getEmail());
            edPhone.setText(String.valueOf(student.getPhone()));
            edAddress.setText(student.getAddress());
            edComments.setText(student.getComments());
        }
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.bt_save:
                //TODO Habría que ver cómo guardar en BBDD algún alumno sin campos (Supongo que me estará guardando strings vacías...)
                //TODO Si se intenta insertar un campo obligatorio (nombre o precio) subrayarlo en rojo. Si no, quitar el subrayado...
                String campo;
                campo = edName.getText().toString();
                if (campo.equals("")) {
                    break;
                }
                String name = campo;
                campo = edPrice.getText().toString();
                if (campo.equals("")) {
                    break;
                }
                Float price = Float.parseFloat(campo);
                Long phone = 0L;
                campo = edPhone.getText().toString();
                if (!campo.equals("")) {
                    phone = Long.parseLong(campo);
                }
                datasource.open();
                if (student_id > 0) {
                    datasource.updateStudent(student_id, name,
                            edSubject.getSelectedItemId()+1L,
                            price,
                            edEmail.getText().toString(),
                            phone,
                            edAddress.getText().toString(),
                            edComments.getText().toString());
                } else {
                    datasource.createStudent(name,
                            edSubject.getSelectedItemId()+1L,
                            price,
                            edEmail.getText().toString(),
                            phone,
                            edAddress.getText().toString(),
                            edComments.getText().toString());
                }
                datasource.close();
                Intent i = new Intent(EditStudent.this, Start.class);
                startActivity(i);
                break;
            case R.id.bt_delete:
                Intent intent = new Intent(EditStudent.this, Start.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
