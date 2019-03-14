package mx.edu.ittepic.tpdm_u2_practica2_delarosaguerrerogabriela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3pconsulta extends AppCompatActivity {
    EditText telefono,nombre,domicilio,fecha,descripcion,fechaSeg,tipo;
    Button elimina,modifica,regresa;
    Propietario propietario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pconsulta);

        telefono = findViewById(R.id.telefono);
        telefono.setEnabled(false);
        nombre = findViewById(R.id.nombre);
        domicilio = findViewById(R.id.domicilio);
        fecha = findViewById(R.id.fecha);
        descripcion = findViewById(R.id.descripcion);
        fechaSeg = findViewById(R.id.fechaSeg);
        tipo = findViewById(R.id.tipo);

        elimina = findViewById(R.id.elimina);
        modifica = findViewById(R.id.modifica);
        regresa = findViewById(R.id.regresa);

        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main3pconsulta.this,Mainppropietario.class);
                startActivity(i);
                finish();
            }
        });

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario prop=new Propietario(Main3pconsulta.this);
                if (prop.eliminar(propietario)){
                    Toast.makeText(Main3pconsulta.this,"Propietario Eliminado Exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main3pconsulta.this,Mainppropietario.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main3pconsulta.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });
        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario propietario=new Propietario(Main3pconsulta.this);
                if (propietario.modificar(new Propietario(telefono.getText().toString(),nombre.getText().toString(),domicilio.getText().toString(),fecha.getText().toString()))){
                    Toast.makeText(Main3pconsulta.this,"Propietario Modificado Exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main3pconsulta.this,Mainppropietario.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main3pconsulta.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    protected void onStart(){
        super.onStart();
        Bundle extras=getIntent().getExtras();
        if (extras != null) {
            propietario=new Propietario(extras.getString("tel"),extras.getString("nombre"),extras.getString("dom"),extras.getString("fecha"));
            nombre.setText(propietario.getNombre());
            domicilio.setText(propietario.getDomicilio());
            fecha.setText(propietario.getFecha());
            telefono.setText(propietario.getTelefono());
        }


    }

}
