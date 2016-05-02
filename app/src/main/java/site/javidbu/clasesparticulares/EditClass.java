package site.javidbu.clasesparticulares;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

public class EditClass extends AppCompatActivity {
    private Long class_id, student_id;
    private EditText class_duration;
    private DatePicker class_date;
    private CheckBox class_paid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);
        class_id = getIntent().getLongExtra("class_id", 0L);
        student_id = getIntent().getLongExtra("student_id", 0L);
        class_duration = (EditText)findViewById(R.id.class_duration);
        class_date = (DatePicker)findViewById(R.id.class_date);
        class_paid = (CheckBox)findViewById(R.id.class_paid);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_save:
                //TODO Save class
                break;
            case R.id.bt_discard:
                Intent i = new Intent(EditClass.this, ViewStudent.class);
                i.putExtra("student_id", student_id);
                startActivity(i);
                break;
        }
    }
}
