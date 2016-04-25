package site.javidbu.clasesparticulares;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class EditStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        EditText edName = (EditText) findViewById(R.id.edit_name);
        Spinner edSubject = (Spinner) findViewById(R.id.edit_subject);
        EditText edPrice = (EditText) findViewById(R.id.edit_price);
        EditText edEmail = (EditText) findViewById(R.id.edit_email);
        EditText edPhone = (EditText) findViewById(R.id.edit_phone);
        EditText edAddress = (EditText) findViewById(R.id.edit_address);
        EditText edComments = (EditText) findViewById(R.id.edit_comments);

        //TODO implement button listeners (save or delete)

    }
}
