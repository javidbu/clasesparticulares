package site.javidbu.clasesparticulares;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class EditStudent extends AppCompatActivity {

    private EditText edName;
    private Spinner edSubject;
    private EditText edPrice;
    private EditText edEmail;
    private EditText edPhone;
    private EditText edAddress;
    private EditText edComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        edName = (EditText) findViewById(R.id.edit_name);
        edSubject = (Spinner) findViewById(R.id.edit_subject);
        edPrice = (EditText) findViewById(R.id.edit_price);
        edEmail = (EditText) findViewById(R.id.edit_email);
        edPhone = (EditText) findViewById(R.id.edit_phone);
        edAddress = (EditText) findViewById(R.id.edit_address);
        edComments = (EditText) findViewById(R.id.edit_comments);
        //TODO llenar el spinner con las asignaturas
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.bt_save:
                StudentDataSource datasource = new StudentDataSource(this);
                datasource.open();
                //FIXME da fallos al intentar meter un contacto sin telefono, y supongo que sin ninguno de los campos numericos
                //TODO Verificar que el alumno tenga los campos necesarios
                //TODO Habría que ver cómo guardar en BBDD algún alumno sin campos (Supongo que me estará guardando strings vacías...
                String campo;
                campo = edName.getText().toString();
                if (campo.equals("")) {
                    break;
                }
                String name = campo;
                campo = edPrice.getText().toString();
                if (campo.equals("")) {
                    break;
                }
                Float price = Float.parseFloat(campo);
                Long phone = 0L;
                campo = edPhone.getText().toString();
                if (!campo.equals("")) {
                    phone = Long.parseLong(campo);
                }
                datasource.createStudent(name,
                        1, //edSubject.getSelectedItemId(),
                        price,
                        edEmail.getText().toString(),
                        phone,
                        edAddress.getText().toString(),
                        edComments.getText().toString());
                datasource.close();
                Intent i = new Intent(EditStudent.this, Start.class);
                startActivity(i);
                break;
            case R.id.bt_delete:
                Intent intent = new Intent(EditStudent.this, Start.class);
                startActivity(intent);
                break;
        }
    }
}
