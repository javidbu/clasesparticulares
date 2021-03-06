package site.javidbu.clasesparticulares;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewStudent extends AppCompatActivity {
    private Long student_id;
    private TextView tvName, tvSubject, tvPrice, tvEmail, tvPhone, tvAddress, tvComments;
    private StudentDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        datasource = new StudentDataSource(this);
        student_id = getIntent().getLongExtra("student_id", 0);
        tvName = (TextView) findViewById(R.id.view_name);
        tvSubject = (TextView) findViewById(R.id.view_subject);
        tvPrice = (TextView) findViewById(R.id.view_price);
        tvEmail = (TextView) findViewById(R.id.view_email);
        tvPhone = (TextView) findViewById(R.id.view_phone);
        tvAddress = (TextView) findViewById(R.id.view_address);
        tvComments = (TextView) findViewById(R.id.view_comments);
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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_edit:
                Intent i = new Intent(ViewStudent.this, EditStudent.class);
                i.putExtra("student_id", student_id);
                startActivity(i);
                break;
            case R.id.bt_add_class:
                Intent intent = new Intent(ViewStudent.this, EditClass.class);
                intent.putExtra("class_id", 0L);
                intent.putExtra("student_id", student_id);
                startActivity(intent);
                break;
            case R.id.bt_view_classes:
                Intent i2 = new Intent(ViewStudent.this, ViewClasses.class);
                i2.putExtra("student_id", student_id);
                startActivity(i2);
                break;
            case R.id.bt_delete:
                datasource.deleteStudentFromId(student_id);
                finish();
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
