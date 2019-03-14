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

public class Main5sconsulta extends AppCompatActivity {
    EditText descripcion,fecha,tipo;
    Button elimina,modifica,regresa;
    Seguro seguro;
    Spinner telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sconsulta);

        descripcion = findViewById(R.id.descripcion);
        fecha = findViewById(R.id.fechaSeg);
        tipo = findViewById(R.id.tipo);
        elimina = findViewById(R.id.elimina);
        modifica = findViewById(R.id.modifica);
        regresa = findViewById(R.id.regresa);
        telefono=findViewById(R.id.comboProp);
        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main5sconsulta.this,Main6pseguro.class);
                startActivity(i);
                finish();
            }
        });

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seguro seg=new Seguro(Main5sconsulta.this);
                if (seg.eliminar(seguro)){
                    Toast.makeText(Main5sconsulta.this,"Eliminado Exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main5sconsulta.this,Main6pseguro.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main5sconsulta.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });
        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seguro seg=new Seguro (Main5sconsulta.this);
                if (seg.modificar(new Seguro(seguro.getIdseguro(),descripcion.getText().toString(),fecha.getText().toString(),tipo.getText().toString(),telefono.getSelectedItem().toString().split("-")[0]))){
                    Toast.makeText(Main5sconsulta.this,"Seguro Modificado Exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main5sconsulta.this,Main6pseguro.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main5sconsulta.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    protected void onStart(){
        super.onStart();
        Bundle extras=getIntent().getExtras();
        String tel=extras.getString("telefono");
        seguro=new Seguro(extras.getInt("idseguro"),extras.getString("des"),extras.getString("fecha"),extras.getString("tipo"),tel);
        descripcion.setText(seguro.getDescripcion());
        tipo.setText(seguro.getTipo());
        fecha.setText(seguro.getFecha());
        Propietario propietario=new Propietario(Main5sconsulta.this);
        Propietario propietarios[]=propietario.consultar();
        if (propietarios!=null){
            String d[]=new String[propietarios.length];
            int pos=0;
            for (int i=0;i<d.length;i++){
                String dato=propietarios[i].getTelefono();
                if (dato.equals(tel)){pos=i;}
                d[i]=dato+"-"+propietarios[i].getNombre();
            }
            ArrayAdapter<String> adap=new ArrayAdapter<String>(Main5sconsulta.this,android.R.layout.simple_list_item_1,d);
            telefono.setAdapter(adap);
            telefono.setSelection(pos);
        }

    }
}
