package site.javidbu.clasesparticulares;

//TODO Botones para editar o eliminar un alumno, y otro para añadirle clases o ver las que lleva

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ViewStudent extends AppCompatActivity {
    private Long student_id;
    private TextView tvName, tvSubject, tvPrice, tvEmail, tvPhone, tvAddress, tvComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        student_id = getIntent().getLongExtra("student_id", 0);
        tvName = (TextView) findViewById(R.id.view_name);
        tvSubject = (TextView) findViewById(R.id.view_subject);
        tvPrice = (TextView) findViewById(R.id.view_price);
        tvEmail = (TextView) findViewById(R.id.view_email);
        tvPhone = (TextView) findViewById(R.id.view_phone);
        tvAddress = (TextView) findViewById(R.id.view_address);
        tvComments = (TextView) findViewById(R.id.view_comments);
        StudentDataSource datasource = new StudentDataSource(this);
        datasource.open();
        Student student = datasource.getStudent(student_id);
        datasource.close();
        tvName.setText(student.getName());
        tvSubject.setText(student.getSubject());
        tvPrice.setText(String.format(getString(R.string.view_price), student.getPrice()));
        tvEmail.setText(student.getEmail());
        tvPhone.setText(String.format(getString(R.string.view_phone), student.getPhone()));
        tvAddress.setText(student.getAddress());
        tvComments.setText(student.getComments());
    }
}
