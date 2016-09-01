package py.edu.facitec.gestordetareas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText nombreEditText;
    EditText emailEditText;
    Button guardarButton;
    ListView usuariosListView;
    List<String> usuarios = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombreEditText = (EditText) findViewById(R.id.editTextNombre);
        emailEditText = (EditText) findViewById(R.id.editTextEmail);
        guardarButton = (Button) findViewById(R.id.buttonGuardar);

        usuariosListView = (ListView)findViewById(R.id.listViewUsuarios);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,usuarios);


        usuariosListView.setAdapter(adapter);


        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreEditText.getText().toString();
                String email = emailEditText.getText().toString();

                //Todo Validacion
                if (!validarNombre(nombre)) {
                    //tratar error
                    nombreEditText.setError(getString(R.string.nombre_error));
                   /*String mensaje = "Hola " + nombre + " " + email;

                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                }else {*/
                    //error
                }

                if(!validarEmail(email)){
                    emailEditText.setError(getString(R.string.email_error));
                } else{
                    String mensaje =  getString(R.string.Bienvenido_msje)+ " " + nombre + " " + email;

                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();

                    String datos = nombre+""+email;

                    usuarios.add(datos);
                    adapter.notifyDataSetChanged();

                    nombreEditText.setText(null);
                    emailEditText.setText(null);
                }

            }
        });
    }



    private boolean validarNombre(String nombre) {

        return !nombre.equals("");
    }

    private boolean validarEmail(String email) {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();

         }
    }