package site.javidbu.clasesparticulares;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class EditHomework extends AppCompatActivity {
    private Long homework_id, student_id;
    private EditText homework_name;
    private CheckBox homework_made;
    private StudentDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_homework);
        homework_id = getIntent().getLongExtra("homework_id", 0L);
        student_id = getIntent().getLongExtra("student_id", 0L);
        homework_made = (CheckBox)findViewById(R.id.homework_made);
        homework_name = (EditText)findViewById(R.id.homework_name);
        dataSource = new StudentDataSource(this);
        if (homework_id > 0L) {
            dataSource.open();
            Homework deberes = dataSource.getHomework(homework_id);
            homework_made.setChecked(deberes.getDone() > 0L);
            homework_name.setText(deberes.getName());
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_save:
                Long done = homework_made.isChecked() ? 1L : 0L;
                String name = homework_name.getText().toString();
                dataSource.createOrUpdateHomework(homework_id, student_id, name, done);
                finish();
                break;
            case R.id.bt_discard:
                finish();
                break;
        }
    }

    @Override
    public void onPause() {
        dataSource.close();
        super.onPause();
    }

    @Override
    public void onResume() {
        dataSource.open();
        super.onResume();
    }
}