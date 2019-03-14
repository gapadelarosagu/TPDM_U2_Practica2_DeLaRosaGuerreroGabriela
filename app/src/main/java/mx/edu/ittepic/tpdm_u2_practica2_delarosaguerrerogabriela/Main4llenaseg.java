package mx.edu.ittepic.tpdm_u2_practica2_delarosaguerrerogabriela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main4llenaseg extends AppCompatActivity {
    EditText descripcion,fecha,tipo;
    Button btninserta;
    Spinner telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llenaseg);

        descripcion = findViewById(R.id.descripcion);
        fecha = findViewById(R.id.fechaSeg);
        tipo = findViewById(R.id.tipo);
        btninserta = findViewById(R.id.btninserta);
        telefono=findViewById(R.id.combo);
        btninserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seguro seguro=new Seguro(Main4llenaseg.this);
                if (seguro.insertar(new Seguro(1,descripcion.getText().toString(),fecha.getText().toString(),tipo.getText().toString(),telefono.getSelectedItem().toString().split("-")[0]))){
                    Toast.makeText(Main4llenaseg.this,"Seguro Insertado Exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main4llenaseg.this,Main6pseguro.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
    protected void onStart(){
        super.onStart();
        Propietario propietario=new Propietario(Main4llenaseg.this);
        Propietario propietarios[]=propietario.consultar();
        if (propietarios!=null){
            String d[]=new String[propietarios.length];
            for (int i=0;i<d.length;i++){
                d[i]=propietarios[i].getTelefono()+"-"+propietarios[i].getNombre();
            }
            ArrayAdapter<String> adap=new ArrayAdapter<String>(Main4llenaseg.this,android.R.layout.simple_list_item_1,d);
            telefono.setAdapter(adap);
        }
        else{
            Toast.makeText(this, "No existen propietarios registrados, favor de registrarlos", Toast.LENGTH_LONG).show();
            Intent i=new Intent(Main4llenaseg.this,Main7principal.class);
            startActivity(i);
            finish();
        }
    }
}
