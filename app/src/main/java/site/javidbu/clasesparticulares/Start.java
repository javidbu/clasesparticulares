package site.javidbu.clasesparticulares;

//TODO actividad para añadir clases
//TODO actividad para ver las clases

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class Start extends AppCompatActivity {
    private StudentDataSource datasource;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        datasource = new StudentDataSource(this);
        datasource.open();

        List<Student> valores = datasource.getAllStudents();
        datasource.close();

        lista = (ListView)findViewById(R.id.lv_students);

        StudentAdapter adapter = new StudentAdapter(valores, this);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int pos, long id) {
                long student_id = ((Student)a.getItemAtPosition(pos)).getId();
                Intent i = new Intent(Start.this, ViewStudent.class);
                i.putExtra("student_id", student_id);
                startActivity(i);
            }
        });
    }

    public void onClick(View view){
        @SuppressWarnings("unchecked")
        StudentAdapter adapter = (StudentAdapter)lista.getAdapter();
        Student student;
        switch (view.getId()) {
            case R.id.add:
                Intent i = new Intent(Start.this, EditStudent.class);
                i.putExtra("student_id", 0L);
                startActivity(i);
                break;
            case R.id.delete:
                if (lista.getAdapter().getCount() > 0) {
                    student = (Student) lista.getAdapter().getItem(0);
                    datasource.deleteStudent(student);
                    adapter.remove(student);
                }
                break;
        }
        adapter.notifyDataSetChanged();
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
