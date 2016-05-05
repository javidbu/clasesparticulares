package site.javidbu.clasesparticulares;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;

import java.util.List;

public class ViewClasses extends AppCompatActivity {
    private Long student_id;
    private Switch switch_classes;
    private StudentDataSource datasource;
    private ListView lista;
    private List<Class> classes;
    private ClassAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classes);
        switch_classes = (Switch)findViewById(R.id.paid_classes);

        student_id = getIntent().getLongExtra("student_id", 0);

        datasource = new StudentDataSource(this);
        datasource.open();
        if (student_id > 0L) {
            classes = datasource.getAllStudentsUnpaidClasses(student_id);
        } else {
            classes = datasource.getAllUnpaidClasses();
        }
        datasource.close();

        lista = (ListView)findViewById(R.id.lv_view_classes);

        adapter = new ClassAdapter(classes, this);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int pos, long id) {
               Class aClass = (Class) a.getItemAtPosition(pos);
                Intent i = new Intent(ViewClasses.this, EditClass.class);
                i.putExtra("student_id", aClass.getStudent_id());
                i.putExtra("class_id", aClass.getId());
                startActivity(i);
            }
        });
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.paid_classes:
                if (switch_classes.isChecked()) {
                    if (student_id > 0L) {
                        classes = datasource.getAllStudentsClasses(student_id);
                    } else {
                        classes = datasource.getAllClasses();
                    }
                } else {
                    if (student_id > 0L) {
                        classes = datasource.getAllStudentsUnpaidClasses(student_id);
                    } else {
                        classes = datasource.getAllUnpaidClasses();
                    }
                }
                adapter.clear();
                adapter.addAll(classes);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onResume() {
        datasource.open();
        if (switch_classes.isChecked()) {
            if (student_id > 0L) {
                classes = datasource.getAllStudentsClasses(student_id);
            } else {
                classes = datasource.getAllClasses();
            }
        } else {
            if (student_id > 0L) {
                classes = datasource.getAllStudentsUnpaidClasses(student_id);
            } else {
                classes = datasource.getAllUnpaidClasses();
            }
        }
        adapter.clear();
        adapter.addAll(classes);
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
