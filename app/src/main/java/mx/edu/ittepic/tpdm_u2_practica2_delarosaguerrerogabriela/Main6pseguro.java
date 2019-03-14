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

public class Main6pseguro extends AppCompatActivity {
    ListView lista;
    Seguro[] listaseguros;
    Button regresa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pseguro);

        lista = findViewById(R.id.listaSeguro);
        regresa = findViewById(R.id.regresa);

        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main6pseguro.this,Main7principal.class);
                startActivity(i);
                finish();
            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(Main6pseguro.this);
                alerta.setTitle("Alerta")
                        .setMessage("¿Desea modificar/eliminar el seguro seleccionado?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent datos=new Intent(Main6pseguro.this,Main5sconsulta.class);
                                datos.putExtra("idseguro",listaseguros[position].getIdseguro());
                                datos.putExtra("des",listaseguros[position].getDescripcion());
                                datos.putExtra("fecha",listaseguros[position].getFecha());
                                datos.putExtra("tipo",listaseguros[position].getTipo());
                                datos.putExtra("telefono",listaseguros[position].getTelefono());
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
        Seguro seguro = new Seguro(this);
        Seguro vector[] = seguro.consultar();
        listaseguros=vector;
        String[] descripcion = null;

        if(vector==null){
            descripcion = new String[1];
            descripcion[0]= "No hay seguros capturados";
            lista.setEnabled(false);
        }else {
            descripcion= new String[vector.length];
            for (int i=0; i<vector.length; i++){
                Seguro temp = vector[i];
                descripcion[i] = temp.getDescripcion();
            }
            lista.setEnabled(true);
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,descripcion);
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
                Intent inserta = new Intent(this,Main4llenaseg.class);
                startActivity(inserta);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
