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

        // use the SimpleCursorAdapter to show the elements in a ListView
        //TODO Enterarme de como cambiar esto para enseñar lo que quiera...
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, valores);
        setListAdapter(adapter);
    }

    public void onClick(View view){
        @SuppressWarnings("unchecked")
        ArrayAdapter<Student> adapter = (ArrayAdapter<Student>)getListAdapter();
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
