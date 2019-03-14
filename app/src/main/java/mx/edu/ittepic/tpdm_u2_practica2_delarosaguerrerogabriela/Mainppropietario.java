package mx.edu.ittepic.tpdm_u2_practica2_delarosaguerrerogabriela;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Mainppropietario extends AppCompatActivity {
    ListView lista;
    Propietario[] listaPropietarios;
    Button regresa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ppropietarios);

        lista= findViewById(R.id.listaPropietario);
        regresa = findViewById(R.id.regresa);

        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Mainppropietario.this,Main7principal.class);
                startActivity(i);
                finish();
            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(Mainppropietario.this);
                alerta.setTitle("Alerta")
                        .setMessage("¿Desea modificar/eliminar el seguro seleccionado?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent datos=new Intent(Mainppropietario.this,Main3pconsulta.class);
                                datos.putExtra("tel",listaPropietarios[position].getTelefono());
                                datos.putExtra("nombre",listaPropietarios[position].getNombre());
                                datos.putExtra("dom",listaPropietarios[position].getDomicilio());
                                datos.putExtra("fecha",listaPropietarios[position].getFecha());
                                startActivity(datos);
                                finish();
                            }
                        })
                        .setNegativeButton("NO",null).show();
            }
        });
    }

    protected void onStart() {
        super.onStart();//se ejecuta cuando se ve la pantalla
        Propietario propietario = new Propietario(this);
        Propietario vector[] = propietario.consultar();
        listaPropietarios=vector;
        String[] telefono = null;

        if(vector==null){
            telefono = new String[1];
            telefono[0]= "No hay propietarios capturados";
            lista.setEnabled(false);
        }else {
            telefono= new String[vector.length];
            for (int i=0; i<vector.length; i++){
                Propietario temp = vector[i];
                telefono[i] = temp.getTelefono();
            }
            lista.setEnabled(true);
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,telefono);
        lista.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insertar:
                Intent insertar = new Intent(this,Main2llenaprop.class);
                startActivity(insertar);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
