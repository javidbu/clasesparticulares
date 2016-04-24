package site.javidbu.clasesparticulares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Javi on 24/04/2016.
 * Adapter to fill in the student list
 */
public class StudentAdapter extends ArrayAdapter<Student> {
    private List<Student> studentList;
    private Context context;

    public StudentAdapter(List<Student> studentList, Context ctx) {
        super(ctx, R.layout.student_element, studentList);
        this.studentList = studentList;
        this.context = ctx;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {//First time view is created
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.student_element, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.st_name);
        TextView tvDebt = (TextView) convertView.findViewById(R.id.st_debt);
        TextView tvClasses = (TextView) convertView.findViewById(R.id.st_classes);
        Student s = studentList.get(position);

        tvName.setText(s.getName());
        tvDebt.setText(String.valueOf(s.getDebt()));
        tvClasses.setText(String.valueOf(s.getClasses()));

        return convertView;
    }
}
