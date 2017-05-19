package apestegui.alejandro.com.a06_recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PersonaEditar extends AppCompatActivity {

    private EditText etNombre, etApellido, etEdad, etDocumento;
    private Button btnGrabar, btnEliminar;
    private String id;
    public static final String EDITAR = "EDITAR";
    public static final String ELIMINAR = "ELIMINAR";

    private View.OnClickListener btnEliminarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("id", id);
            intent.putExtra("accion", ELIMINAR);
            setResult(RESULT_OK, intent);
            finish();
        }
    };
    private View.OnClickListener btnGrabarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!validar()) {
                Toast.makeText(PersonaEditar.this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("id", id);
            intent.putExtra("accion", EDITAR);
            intent.putExtra("nombre", etNombre.getText().toString());
            intent.putExtra("apellido", etApellido.getText().toString());
            intent.putExtra("edad", etEdad.getText().toString());
            intent.putExtra("documento", etDocumento.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    };


    private boolean validar() {
        boolean valida = true;

        if(TextUtils.isEmpty(etNombre.getText().toString().trim())){
            valida = false;
        }
        if(TextUtils.isEmpty(etApellido.getText().toString().trim())){
            valida = false;
        }
        if(TextUtils.isEmpty(etEdad.getText())){
            valida = false;
        }
        if(TextUtils.isEmpty(etDocumento.getText().toString().trim())){
            valida = false;
        }

        return valida;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persona_editar);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etEdad = (EditText) findViewById(R.id.etEdad);
        etDocumento = (EditText) findViewById(R.id.etDocumento);
        btnGrabar = (Button) findViewById(R.id.btnGrabar);
        btnGrabar.setOnClickListener(btnGrabarOnClickListener);
        btnEliminar = (Button) findViewById(R.id.btnELiminar);
        btnEliminar.setOnClickListener(btnEliminarOnClickListener);


        Intent intent = getIntent();
        etNombre.setText(intent.getStringExtra("nombre"));
        etApellido.setText(intent.getStringExtra("apellido"));
        etDocumento.setText(intent.getStringExtra("documento"));
        int edad = intent.getIntExtra("edad", -1);
        String edadString;
        if(edad==-1)edadString = "";
        else edadString = ""+edad;
        etEdad.setText(edadString);

        id = intent.getStringExtra("id");
        if (id == null) {
            btnEliminar.setVisibility(View.INVISIBLE);
        }

    }

}
