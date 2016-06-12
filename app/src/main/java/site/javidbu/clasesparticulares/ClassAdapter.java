package site.javidbu.clasesparticulares;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by Javi on 02/05/2016.
 * Adapter for the class list
 */
public class ClassAdapter extends ArrayAdapter<Class> {
    private List<Class> classList;
    private Context context;

    public ClassAdapter(List<Class> classList, Context ctx) {
        super(ctx, R.layout.student_element, classList);
        this.classList = classList;
        this.context = ctx;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {//First time view is created
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.class_element, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.cl_date);
        TextView tvInfo = (TextView) convertView.findViewById(R.id.cl_info);
        LinearLayout llBackground = (LinearLayout) convertView.findViewById(R.id.cl_background);
        Class c = classList.get(position);

        String date = DateFormat.getDateInstance(DateFormat.FULL).format(c.getPrintable_date().getTime());
        date = date.substring(0, 1).toUpperCase() + date.substring(1);
        tvName.setText(date);
        tvInfo.setText(String.format(getContext().getString(R.string.cl_info), c.getDuration()));
        if(c.getPaid() == 1.0) {
            llBackground.setBackgroundColor(Color.parseColor("#CFFAAA"));
        } else {

            llBackground.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        return convertView;
    }
}
