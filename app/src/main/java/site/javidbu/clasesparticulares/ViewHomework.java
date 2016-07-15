package site.javidbu.clasesparticulares;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ViewHomework extends AppCompatActivity {
    private Long student_id;
    private StudentDataSource datasource;
    private List<Homework> homework; //TODO Hacer class Homework (ver Class) y su adapter
    private HomeworkAdapter adapter;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_homework);
        student_id = getIntent().getLongExtra("student_id");

        datasource = new StudentDataSource(this);
        datasource.open();
        homework = datasource.getAllStudentsHomework(student_id); //TODO Preparar la BBDD para que incluya homework, con su método y todo
        datasource.close();

        lista = (ListView)findViewById(R.id.lv_view_homework);
        lista.setEmptyView(findViewById(android.R.id.empty));

        adapter = new HomeworkAdapter(homework, this);
        lista.setAdapter(adapter);

        /*lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int pos, long id) {
                Homework aHomework = (Homework) a.getItemAtPosition(pos);
                Intent i = new Intent(ViewClasses.this, EditClass.class);
                i.putExtra("student_id", aClass.getStudent_id());
                i.putExtra("class_id", aClass.getId());
                startActivity(i);
            }
        });*/ //TODO Preparar onClick listener
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
