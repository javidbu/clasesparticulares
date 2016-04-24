package site.javidbu.clasesparticulares;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

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
                student = datasource.createStudent("Juan", 1, 15, "juan@juan.com", 987654321, "calle, ciudad, España", "Nada que objetar");
                adapter.add(student);
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
