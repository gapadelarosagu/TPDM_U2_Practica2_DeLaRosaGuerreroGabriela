package mx.edu.ittepic.tpdm_u2_practica2_delarosaguerrerogabriela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2llenaprop extends AppCompatActivity {
    EditText telefono,nombre,domicilio,fecha;
    Button btninserta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llenaprop);

        telefono = findViewById(R.id.telefono);
        nombre = findViewById(R.id.nombre);
        domicilio = findViewById(R.id.domicilio);
        fecha = findViewById(R.id.fecha);
        btninserta = findViewById(R.id.btninserta);

        btninserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario propietario=new Propietario(Main2llenaprop.this);
                if (propietario.insertar(new Propietario(telefono.getText().toString(),nombre.getText().toString(), domicilio.getText().toString(), fecha.getText().toString()))){
                    Toast.makeText(Main2llenaprop.this,"Propietario Insertado Exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main2llenaprop.this,Mainppropietario.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main2llenaprop.this, "No se pudo insertar", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
