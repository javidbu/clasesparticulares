package site.javidbu.clasesparticulares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        Class c = classList.get(position);

        tvName.setText(c.getPrintable_date().toString());
        //TODO Poner la fecha en castellano cuando proceda...
        tvInfo.setText(String.format(getContext().getString(R.string.cl_info), c.getDuration()));
        //TODO Cambiar el color del fondo para indicar si está pagada

        return convertView;
    }
}
