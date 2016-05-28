package site.javidbu.clasesparticulares;

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
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        datasource = new StudentDataSource(this);
        datasource.open();

        List<Student> valores = datasource.getAllStudents();
        datasource.close();

        lista = (ListView)findViewById(R.id.lv_students);
        lista.setEmptyView(findViewById(android.R.id.empty));

        adapter = new StudentAdapter(valores, this);
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
        switch (view.getId()) {
            case R.id.add:
                Intent i = new Intent(Start.this, EditStudent.class);
                i.putExtra("student_id", 0L);
                startActivity(i);
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        adapter.clear();
        adapter.addAll(datasource.getAllStudents());
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
