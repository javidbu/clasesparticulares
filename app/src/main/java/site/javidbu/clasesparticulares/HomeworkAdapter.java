package site.javidbu.clasesparticulares;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by Javi on 02/05/2016.
 * Adapter for the class list
 */
public class HomeworkAdapter extends ArrayAdapter<Homework> {
    private List<Homework> homeworkList;
    private Context context;

    public HomeworkAdapter(List<Homework> homeworkList, Context ctx) {
        super(ctx, R.layout.student_element, homeworkList);
        this.homeworkList = homeworkList;
        this.context = ctx;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {//First time view is created
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.homework_element, parent, false);
        }
        CheckBox cbHomework = (CheckBox) convertView.findViewById(R.id.homework_element);
        Homework h = homeworkList.get(position);

        cbHomework.setText(h.getName());
        cbHomework.setChecked(h.getDone() == 1.0);

        return convertView;
    }
}