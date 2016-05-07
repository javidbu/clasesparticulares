package site.javidbu.clasesparticulares;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EditClass extends AppCompatActivity {
    private Long class_id, student_id;
    private EditText class_duration, class_comments;
    private DatePicker class_date;
    private CheckBox class_paid;
    private StudentDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);
        class_id = getIntent().getLongExtra("class_id", 0L);
        student_id = getIntent().getLongExtra("student_id", 0L);
        class_duration = (EditText)findViewById(R.id.class_duration);
        class_date = (DatePicker)findViewById(R.id.class_date);
        class_paid = (CheckBox)findViewById(R.id.class_paid);
        class_comments = (EditText)findViewById(R.id.class_comments);
        dataSource = new StudentDataSource(this);
        if (class_id > 0L) {
            dataSource.open();
            Class clase = dataSource.getClass(class_id);
            class_duration.setText(String.valueOf(clase.getDuration()));
            class_date.updateDate(clase.getPrintable_date().get(Calendar.YEAR),
                    clase.getPrintable_date().get(Calendar.MONTH),
                    clase.getPrintable_date().get(Calendar.DATE));
            class_paid.setChecked(clase.getPaid() > 0L);
            class_comments.setText(clase.getComments());
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_save:
                Long unix_date = getUnixDate(class_date);
                Float duration = Float.parseFloat(class_duration.getText().toString());
                Long paid = class_paid.isChecked() ? 1L : 0L;
                String comments = class_comments.getText().toString();
                dataSource.createOrUpdateClass(class_id, student_id, unix_date, duration, paid, comments);
                finish();
                break;
            case R.id.bt_discard:
                finish();
                break;
        }
    }

    public static Long getUnixDate(DatePicker datepicker) {
        int day = datepicker.getDayOfMonth();
        int month = datepicker.getMonth();
        int year = datepicker.getYear();
        Calendar c = GregorianCalendar.getInstance();
        c.set(year, month, day);
        return c.getTimeInMillis()/1000L;
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