package apestegui.alejandro.com.a06_recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements RVAdapterPersona.onClickRVAdapterPersona {

    private RecyclerView rvPersona;
    private Button btnGrabar;
    private RVAdapterPersona rVAdapterPersona;
    private static final int EDITAR = 1;
    private static final int NUEVO = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == NUEVO) {
                Persona persona = new Persona();
                persona.setId(UUID.randomUUID().toString());
                persona.setNombre(data.getStringExtra("nombre"));
                persona.setApellido(data.getStringExtra("apellido"));
                persona.setEdad(Integer.parseInt(data.getStringExtra("edad")));
                persona.setDocumento(data.getStringExtra("documento"));
                rVAdapterPersona.agregarPersona(persona);
            }
            if (requestCode == EDITAR) {

                if(data.getStringExtra("accion")!=null){
                    Persona persona = new Persona();
                    persona.setId(data.getStringExtra("id"));

                    if(data.getStringExtra("accion").equals(PersonaEditar.EDITAR)){
                        persona.setNombre(data.getStringExtra("nombre"));
                        persona.setApellido(data.getStringExtra("apellido"));
                        persona.setEdad(Integer.parseInt(data.getStringExtra("edad")));
                        persona.setDocumento(data.getStringExtra("documento"));
                        rVAdapterPersona.modificarPersona(persona);
                    }
                    if(data.getStringExtra("accion").equals(PersonaEditar.ELIMINAR)){
                        rVAdapterPersona.eliminarPersona(persona.getId());
                    }
                }
            }
        }
    }

    private View.OnClickListener btnGrabarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, PersonaEditar.class);
            startActivityForResult(intent, NUEVO);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPersona = (RecyclerView) findViewById(R.id.rvContactos);
        rvPersona.setLayoutManager(new LinearLayoutManager(this));
        rVAdapterPersona = new RVAdapterPersona(this);
        rvPersona.setAdapter(rVAdapterPersona);
        rVAdapterPersona.agregarPersona(new Persona(UUID.randomUUID().toString(), "Alejandro", "Apestegui", 18, "70835139"));
        btnGrabar = (Button) findViewById(R.id.btnNuevo);
        btnGrabar.setOnClickListener(btnGrabarOnClickListener);

    }

    @Override
    public void onClick(Persona persona) {
        Intent intent = new Intent(MainActivity.this, PersonaEditar.class);
        intent.putExtra("id", persona.getId());
        intent.putExtra("nombre", persona.getNombre());
        intent.putExtra("apellido", persona.getApellido());
        intent.putExtra("documento", persona.getDocumento());
        intent.putExtra("edad", persona.getEdad());
        startActivityForResult(intent, EDITAR);
    }
}
