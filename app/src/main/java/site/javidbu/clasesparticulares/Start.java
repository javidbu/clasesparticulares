package site.javidbu.clasesparticulares;

//TODO actividad para crear alumno
//TODO actividad para editar alumno
//TODO actividad para ver alumno
//TODO actividad para añadir clases
//TODO actividad para ver las clases

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class Start extends ListActivity {
    private StudentDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        datasource = new StudentDataSource(this);
        datasource.open();

        List<Student> valores = datasource.getAllStudents();

        StudentAdapter adapter = new StudentAdapter(valores, this);
        setListAdapter(adapter);
    }

    public void onClick(View view){
        @SuppressWarnings("unchecked")
        StudentAdapter adapter = (StudentAdapter)getListAdapter();
        Student student;
        switch (view.getId()) {
            case R.id.add:
                Intent i = new Intent(Start.this, EditStudent.class);
                startActivity(i);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    student = (Student) getListAdapter().getItem(0);
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
